<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const planStore = "planStore";

export default {
  name: "BackgroundMap",
  data() {
    return {
      markers: [],
      infowindow: null,
      customOverlay: null,
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
        center: new kakao.maps.LatLng(36.355, 127.2991),
        level: 5,
      };

      this.map = new kakao.maps.Map(container, options);
      this.drawMapByList();
    },

    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },

    drawMapByList() {
      const positions = this.planList.map(
        ({ latitude, longitude }) => new kakao.maps.LatLng(latitude, longitude)
      );
      console.log(positions);

      this.displayMarker(positions);
    },

    displayMarker(positions) {
      if (this.markers.length > 0) {
        this.markers.map((marker) => marker.setMap(null));
        this.markers = null;
      }

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
              draggable: true,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
        let level = this.map.getLevel();
        this.map.setLevel(level + 1);
        let tmp = this.map.getCenter();

        this.map.setCenter(new kakao.maps.LatLng(tmp.getLat(), tmp.getLng() - 0.02));
      }
    },
    closeOverlay() {
      this.customOverlay.setMap(null);
    },
  },
  computed: {
    ...mapState(planStore, ["planList"]),
  },
  watch: {
    planList() {
      this.drawMapByList();
    },
    // attractionList() {
    //   console.log("watched attractionList");
    //   this.drawMapByList();
    // },
    // mapCenterPos() {
    //   console.log("watched mapCenterPos");
    //   let latlng = new kakao.maps.LatLng(this.mapCenterPos.lat, this.mapCenterPos.lng - 0.02);
    //   this.map.setCenter(latlng);
    // },
    // mapFocusAttractionInfo() {
    //   console.log("watched mapFocusAttractionInfo");
    //   if (this.customOverlay != null) {
    //     this.customOverlay.setMap(null);
    //   }
    //   let title = this.mapFocusAttractionInfo.title;
    //   let addr1 = this.mapFocusAttractionInfo.addr1;
    //   let likeCount = this.mapFocusAttractionInfo.likeCount;
    //   let imageURL = this.mapFocusAttractionInfo.imageURL;
    //   let content = `
    //   <style>
    // .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px; line-height: 1.5; color: black}
    // .wrap * {padding: 0;margin: 0;}
    // .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden; background-color: white;}
    // .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
    // .info .title {padding: 5px 0 0 10px;height: 32px; background-color: rgb(133, 133, 132); border-bottom: 1px solid #ddd; font-size: 18px; font-weight: bold; color:white}
    // .info .body {position: relative;overflow: hidden;}
    // .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
    // .desc .ellipsis {overflow: hidden; text-overflow: ellipsis; white-space: nowrap;font-size: 13px;}
    // .desc .liked {font-size: 16px; color: #888; margin-top: 2px;}
    // .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
    // .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
    // .info .link {color: #42b983; font-weight: bold;}
    // </style>
    // <div class="wrap">
    //     <div class="info">
    //         <div class="title">
    //             ${title}
    //             <div class="close" onclick="closeOverlay()" title="닫기"></div>
    //         </div>
    //         <div class="body">
    //             <div class="img">
    //                 <img src="${imageURL}" width="73" height="70">
    //            </div>
    //             <div class="desc">
    //                 <div class="ellipsis">${addr1}</div>
    //                 <hr>
    //                 <hr>
    //                 <hr>
    //                 <hr>
    //                 <div class="">좋아요 ${likeCount}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp&nbsp&nbsp;
    //                   <a href="${imageURL}" target="_blank" class="link">사진 확대</a></div>
    //             </div>
    //         </div>
    //     </div>
    // </div>`;
    //   let position = new kakao.maps.LatLng(this.mapCenterPos.lat, this.mapCenterPos.lng);
    //   this.customOverlay = new kakao.maps.CustomOverlay({
    //     position: position,
    //     content: content,
    //     xAnchor: 0.3,
    //     yAnchor: 0.91,
    //   });
    //   this.customOverlay.setMap(this.map);
    // },
  },
};
</script>

<style scoped>
#map {
  width: 100vw;
  height: 100vh;
}
</style>
