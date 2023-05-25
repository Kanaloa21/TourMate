<template>
  <div class="position-relative">
    <div id="map" class="position-absolute" style="z-index: 2"></div>
    <b-form-group
      v-slot="{ ariaDescribedby }"
      class="position-absolute content-types cb p-2"
      style="z-index: 3; text-align: left"
    >
      <b-form-checkbox-group
        v-cloak
        v-model="selected"
        :options="options"
        :aria-describedby="ariaDescribedby"
        size="sm"
        stacked
      ></b-form-checkbox-group>
    </b-form-group>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState, mapMutations } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "BackgroundMap",
  data() {
    return {
      markers: [],
      infowindow: null,
      customOverlay: null,
      selected: [12],
      options: [
        { text: "관광지", value: 12 },
        { text: "문화시설", value: 14 },
        { text: "축제공연행사", value: 15 },
        { text: "여행코스", value: 25 },
        { text: "레포츠", value: 28 },
        { text: "숙박", value: 32 },
        { text: "쇼핑", value: 38 },
        { text: "음식점", value: 39 },
      ],
      selectedMarkerIndex: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7b0bf3df5b1386c9db451b529cce6d89";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.mapCenterPos.lat, this.mapCenterPos.lng),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      //this.displayMarker(this.markerPositions1);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    drawMapByList() {
      const positions = this.top10Attractions.map(
        ({ latitude, longitude }) => new kakao.maps.LatLng(latitude, longitude)
      );
      console.log(positions);

      this.displayMarker(positions);
    },

    displayMarker(positions) {
      // if (this.markers.length > 0) {
      //   this.markers.forEach((marker) => marker.setMap(null));
      // }

      // const positions = markerPositions.map((position) => new kakao.maps.LatLng(...position));

      // 이미 마커가 있으면 삭제
      if (this.markers && this.markers.length > 0) {
        this.markers.map((marker) => marker.setMap(null));
        this.markers = null;
      }

      //
      if (positions.length > 0) {
        this.markers = positions.map((position) => {
          const marker = new kakao.maps.Marker({
            map: this.map,
            position,
            draggable: false,
          });

          return marker;
        });

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
        let tmp = this.map.getCenter();
        // this.mapCenterPos = this.map.getCenter();
        this.SET_MAP_CENTER_POS({ lat: tmp.getLat(), lng: tmp.getLng() });
        // let latlng = new kakao.maps.LatLng( - 0.02);
        // this.map.setCenter(latlng);
        // let level = this.map.getLevel();
        // this.map.setLevel(level + 1);
      }
    },
    closeOverlay() {
      this.customOverlay.setMap(null);
    },

    ...mapActions(attractionStore, ["searchAttractionList"]),
    ...mapMutations(attractionStore, [
      "SET_MAP_CENTER_POS",
      "SET_FILTERED_ATTRACTION_LIST",
      "SET_SELECTED",
      "SET_MAP_FOCUS_ATTRACTION_INFO",
    ]),
  },
  computed: {
    ...mapState(attractionStore, [
      "attractionList",
      "mapCenterPos",
      "mapFocusAttractionInfo",
      "filteredAttractionList",
    ]),
    ...mapGetters(attractionStore, ["top10Attractions"]),
  },
  watch: {
    selected() {
      this.SET_SELECTED(this.selected);
      this.SET_FILTERED_ATTRACTION_LIST();
      this.drawMapByList();
    },
    filteredAttractionList() {
      console.log("watched attractionList");
      this.drawMapByList();
    },
    mapCenterPos() {
      console.log("watched mapCenterPos");
      let latlng = new kakao.maps.LatLng(this.mapCenterPos.lat, this.mapCenterPos.lng - 0.02);
      this.map.setCenter(latlng);
    },
    mapFocusAttractionInfo() {
      console.log("watched mapFocusAttractionInfo");
      if (this.customOverlay != null) {
        this.customOverlay.setMap(null);
      }
      let title = this.mapFocusAttractionInfo.title;
      let addr1 = this.mapFocusAttractionInfo.addr1;
      let likeCount = this.mapFocusAttractionInfo.likeCount;
      let imageURL = this.mapFocusAttractionInfo.imageURL;

      let content = `
      <style>
    .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px; line-height: 1.5; color: black}
    .wrap * {padding: 0;margin: 0;}
    .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden; background-color: white;}
    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
    .info .title {padding: 5px 0 0 10px;height: 32px; background-color: rgb(133, 133, 132); border-bottom: 1px solid #ddd; font-size: 18px; font-weight: bold; color:white}
    .info .body {position: relative;overflow: hidden;}
    .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
    .desc .ellipsis {overflow: hidden; text-overflow: ellipsis; white-space: nowrap;font-size: 13px;}
    .desc .liked {font-size: 16px; color: #888; margin-top: 2px;}
    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
    .info .link {color: #42b983; font-weight: bold;}
    </style>
    <div class="wrap">
        <div class="info">
            <div class="title">
                ${title}
                <div class="close" onclick="closeOverlay()" title="닫기"></div>
            </div>
            <div class="body">
                <div class="img">
                    <img src="${imageURL}" width="73" height="70">
               </div>
                <div class="desc">
                    <div class="ellipsis">${addr1}</div>
                    <hr>
                    <hr>
                    <hr>
                    <hr>
                    <div class="">좋아요 ${likeCount}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp&nbsp&nbsp;
                      <a href="${imageURL}" target="_blank" class="link">사진 확대</a></div>
                </div>
            </div>
        </div>
    </div>`;
      let position = new kakao.maps.LatLng(this.mapCenterPos.lat, this.mapCenterPos.lng);
      this.customOverlay = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
        xAnchor: 0.3,
        yAnchor: 0.91,
      });
      this.customOverlay.setMap(this.map);
    },
  },
};
</script>

<style scoped>
#map {
  width: 100vw;
  height: 100vh;
}

.content-types {
  width: 140px;
  left: 500px;
  top: 20px;
  color: white;
  background-color: rgb(133, 133, 132);
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 25%), 0 2px 10px 0 rgb(0 0 0 / 25%);
  border-radius: 10px;
}
</style>
