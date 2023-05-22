function Haversine(p1, p2) {
  let distance;
  let radius = 6371; // 지구 반지름(km)
  let toRadian = Math.PI / 180;

  let deltaLatitude = Math.abs(p1.latitude - p2.latitude) * toRadian;
  let deltaLongitude = Math.abs(p1.longitude - p2.longitude) * toRadian;

  let sinDeltaLat = Math.sin(deltaLatitude / 2);
  let sinDeltaLng = Math.sin(deltaLongitude / 2);
  let squareRoot = Math.sqrt(
    sinDeltaLat * sinDeltaLat +
      Math.cos(x1 * toRadian) * Math.cos(x2 * toRadian) * sinDeltaLng * sinDeltaLng
  );

  distance = 2 * radius * Math.asin(squareRoot);

  return distance;
}

function sortByHaverSine(arr) {
  let sortedArr = [...arr];
  let sortedDist = [];
  for (let i = 0; i < sortedArr.length - 1; i++) {
    let pos = i + 1;
    let shortestIndex = i + 1;
    let shortestPath = 1000000;

    for (let j = i + 1; j < sortedArr.length; j++) {
      let distance = Haversine(sortedArr[i], sortedArr[j]);

      if (shortestPath > distance) {
        shortestIndex = j;
        shortestPath = distance;
      }
    }

    if (shortestIndex != pos) {
      let temp = sortedArr[pos];
      sortedArr[pos] = sortedArr[shortestIndex];
      sortedArr[shortestIndex] = temp;
    }

    sortedDist.push(shortestPath);
  }

  return { sortedArr, sortedDist };
}

export { sortByHaverSine };
