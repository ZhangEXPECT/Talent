<template>
  <div class="container" ref="container"></div>
  <div class="load">Loading...</div>
  <div v-if="status">
    <div class="img1">
      <img src="images/scale.png" />
    </div>
    <div class="roam1">
      <img src="images/roam.png" />
      <div>漫游</div>
    </div>

    <audio class="audio"></audio>
    <div v-if="avatar" class="avatar" @click="goChat">
      <img :src="avatar" />
    </div>
    <div class="footer1">
      <div @mousedown="changeLabel2">
        <img src="images/label.png" />
        <div>{{ label2 }}</div>
      </div>
      <div @mousedown="goBooking">
        <img src="images/booking.png" />
        <div>立即申请</div>
      </div>
      <a
        :href="'tel:' + phone"
        style="text-decoration: none; color: #fff; display: none"
      >
        <img src="images/phone.png" />
        一键呼叫
      </a>
    </div>
    <div class="footer2">
      <div @mousedown="changeLabel1">
        <img src="images/ruler.png" />
        <div>{{ label1 }}</div>
      </div>
      <div @mousedown="doubleHandle">
        <img src="images/vr.png" />
        <div style="margin-top: 10px">VR眼镜</div>
      </div>
    </div>
    <div class="footer3">
      <div @mousedown="changeLabel1">
        <img src="images/ruler.png" />
        <div>{{ label1 }}</div>
      </div>
      <div @mousedown="changeLabel2">
        <img src="images/label.png" />
        <div>{{ label2 }}</div>
      </div>
      <a
        :href="'tel:' + phone"
        style="text-decoration: none; color: #fff; display: none"
      >
        <img src="images/phone.png" />
        一键呼叫
      </a>
      <div @mousedown="doubleHandle">
        <img src="images/vr.png" />
        <div style="margin-top: 10px">VR眼镜</div>
      </div>
    </div>
    <div class="changeRight" @mousedown="changeRight">
      <img src="images/more_right.png" />
    </div>
    <div class="changeLeft" @mousedown="changeLeft">
      <img src="images/more_left.png" />
    </div>
  </div>
  <div v-else>
    <div class="side1">
      <div @mousedown="changeLabel1">
        <img src="images/ruler.png" />
        <div>{{ label1 }}</div>
      </div>
      <div @mousedown="changeLabel2">
        <img src="images/label.png" />
        <div>{{ label2 }}</div>
      </div>
      <div @mousedown="goStore">
        <img src="images/link.png" />
        <!-- <div>商品链接</div> -->
      </div>
      <div @mousedown="goBooking">
        <img src="images/booking.png" />
        <div>立即申请</div>
      </div>
    </div>
    <div class="side2">
      <a
        :href="'tel:' + phone"
        style="text-decoration: none; color: #fff; display: none"
      >
        <img src="images/phone.png" />
        一键呼叫
      </a>
      <div @mousedown="doubleHandle">
        <img src="images/vr.png" />
        <div style="margin-top: 10px">VR眼镜</div>
      </div>
    </div>
    <div class="changeDown" @mousedown="changeDown">
      <img src="images/more_down.png" />
    </div>
    <div class="changeUp" @mousedown="changeUp">
      <img src="images/more_up.png" />
    </div>
  </div>
  <div class="navbar-list">
    <ul class="flex-imd">
      <li
        v-for="(item, index) in photosList"
        :key="index"
        class="navbar-item"
        :data-navid="item.navId"
      >
        <img :src="item.thumbnail" @mousedown="changScene(item, index)" />
        <div>{{ item.name }}</div>
      </li>
    </ul>
  </div>
</template>

<script setup>
import * as THREE from "three";
import { StereoEffect } from "three/examples/jsm/effects/StereoEffect";
import { OrbitControls } from "three/examples/jsm/controls/OrbitControls";
import {
  CSS3DObject,
  CSS3DRenderer,
} from "three/examples/jsm/renderers/CSS3DRenderer";
import wx from "weixin-js-sdk";
import axios from "axios";
import { ref, onMounted } from "vue";

const scene = new THREE.Scene();
const group = new THREE.Group();
scene.add(group);

var fov = 100;
var near = 1;
var far = 1000;
const camera = new THREE.PerspectiveCamera(
  fov,
  document.documentElement.clientWidth / document.documentElement.clientHeight,
  near,
  far
);
camera.position.set(0, 0, 0.1);

const renderer = new THREE.WebGL1Renderer({ antialias: true });
renderer.setPixelRatio(window.devicePixelRatio);
renderer.setSize(window.innerWidth, window.innerHeight);
renderer.setClearColor(0xb9d3ff, 1);
const container = ref(null);

// 定义双屏渲染器
const VrRender = new StereoEffect(renderer);
const render = () => {
  if (vrShow) {
    VrRender.render(scene, camera);
  } else {
    renderer.render(scene, camera);
  }
  requestAnimationFrame(render);
  mesh.rotateY(0.001); //每次绕Y轴旋转0.01弧度
  labelRenderer.render(scene, camera);
};
const geometry = new THREE.SphereGeometry(300, 300, 300);
var material,
  texture,
  mesh,
  test01,
  position01,
  pointLabel01,
  g,
  m,
  pointsArray,
  line,
  test02,
  position02,
  pointLabel02;
let photosList = ref([]);
let audioList = ref([]);
let labelList = ref([]);
let ruleList = ref([]);



function getInfo(id) {
  axios({
    method: "get",
    url: `https://api.meijiawozhu.com/api/front/vr/info/${id}`,
  })
    .then((res) => {
      photosList.value = res.data.data.photos;
      audioList.value = res.data.data.aiAudios;
      labelList.value = res.data.data.labelInfo;
      ruleList.value = res.data.data.ruleInfo;
      var audio = document.querySelector(".audio");
      audio.src = audioList.value[0].audio;
      material = new THREE.MeshBasicMaterial();
      texture = new THREE.TextureLoader().load(
        photosList.value[0].photo,
        function () {
          document.querySelector(".load").style.display = "none";
        }
      );
      material.map = texture;
      mesh = new THREE.Mesh(geometry, material);
      mesh.geometry.scale(1, 1, -1);
      group.add(mesh);
      test01 = labelList.value[0].name + "<br/>" + labelList.value[0].content;
      position01 = labelList.value[0].position;
      pointLabel01 = createLableObj(test01, position01, "", "content");
      g = new THREE.BufferGeometry();
      m = new THREE.LineBasicMaterial({ color: 0xffffff });
      pointsArray = new Array();
      pointsArray.push(
        new THREE.Vector3(
          ruleList.value[0].position[0].x,
          ruleList.value[0].position[0].y,
          ruleList.value[0].position[0].z
        )
      );
      pointsArray.push(
        new THREE.Vector3(
          ruleList.value[0].position[1].x,
          ruleList.value[0].position[1].y,
          ruleList.value[0].position[1].z
        )
      );
      g.setFromPoints(pointsArray);
      line = new THREE.Line(g, m);
      test02 = ruleList.value[0].content.name;
      position02 = ruleList.value[0].content.position;
      pointLabel02 = createLableObj(test02, position02, "y", "laber_name");
    })
    .catch((err) => {
      console.log(err);
    });
}



function changScene(item, index) {
  if (active1) changeLabel1();
  if (active2) changeLabel2();

  if (vrShow) doubleHandle();
  var audio = document.querySelector(".audio");
  if (audioList.value[index]) {
    audio.src = audioList.value[index].audio;
  }
  var timer = setInterval(function () {
    camera.fov -= 1;
    camera.updateProjectionMatrix();
    if (camera.fov == 50) {
      clearInterval(timer);
      camera.fov = 100;
      camera.updateProjectionMatrix();
      document.querySelector(".load").style.display = "block";
      texture = new THREE.TextureLoader().load(item.photo, function () {
        document.querySelector(".load").style.display = "none";
      });
      material.map = texture;
      if (labelList.value[index]) {
        test01 =
          labelList.value[index].name +
          "<br/>" +
          labelList.value[index].content;
        position01 = labelList.value[index].position;
        pointLabel01 = createLableObj(test01, position01, "", "content");
      }
      if (ruleList.value[index]) {
        pointsArray = new Array();
        pointsArray.push(
          new THREE.Vector3(
            ruleList.value[index].position[0].x,
            ruleList.value[index].position[0].y,
            ruleList.value[index].position[0].z
          )
        );
        pointsArray.push(
          new THREE.Vector3(
            ruleList.value[index].position[1].x,
            ruleList.value[index].position[1].y,
            ruleList.value[index].position[1].z
          )
        );
        g.setFromPoints(pointsArray);
        test02 = ruleList.value[index].content.name;
        position02 = ruleList.value[index].content.position;
        if (index == 0) {
          pointLabel02 = createLableObj(test02, position02, "y", "laber_name");
        }
        pointLabel02 = createLableObj(test02, position02, "", "laber_name");
      }
    }
  }, 50);
}

var labelRenderer = new CSS3DRenderer();
labelRenderer.setSize(
  document.documentElement.clientWidth,
  document.documentElement.clientHeight
);
labelRenderer.domElement.style.position = "absolute";
labelRenderer.domElement.style.top = 0;

function createLableObj(text, position, r, c) {
  let laberDiv = document.createElement("div");
  laberDiv.className = c;
  laberDiv.innerHTML = text;
  let pointLabel = new CSS3DObject(laberDiv);
  pointLabel.position.set(position.x, position.y, position.z);
  if (r === "y") {
    pointLabel.rotation.y = Math.PI / 2;
  }
  pointLabel.scale.x = 0.4;
  pointLabel.scale.y = 0.4;
  return pointLabel;
}

let label1 = ref("打开标尺");
let label2 = ref("打开标签");
let active1 = false;
let active2 = false;
function changeLabel1() {
  active1 = !active1;
  if (active1) {
    label1.value = "关闭标尺";
    group.add(line, pointLabel02);
  } else {
    label1.value = "打开标尺";
    group.remove(line, pointLabel02);
  }
}
function changeLabel2() {
  active2 = !active2;
  if (active2) {
    label2.value = "关闭标签";
    group.add(pointLabel01);
  } else {
    label2.value = "打开标签";
    group.remove(pointLabel01);
  }
}

function goBooking() {
  // 微信环境
  wx.miniProgram.getEnv((res) => {
    if (res.miniprogram) {
      console.log(res.systemInfo);
      wx.miniProgram.navigateTo({ url: "/pages/house/qualification/index" });
      return;
    }
  });
}
let vrShow = false;
function doubleHandle() {
  if (vrShow) {
    vrShow = false;
    document.querySelector(".navbar-list").style.display = "block";
    renderer.setViewport(
      0,
      0,
      document.documentElement.clientWidth,
      document.documentElement.clientHeight
    );
  } else {
    vrShow = true;
    document.querySelector(".navbar-list").style.display = "none";
  }
}

let status = ref(1);
function renderResize() {
  let width = document.documentElement.clientWidth;
  let height = document.documentElement.clientHeight;
  if (width > height) {
    status.value = 0;
    onResize();
  } else {
    status.value = 1;
    onResize();
  }
}
function onResize() {
  camera.aspect =
    document.documentElement.clientWidth /
    document.documentElement.clientHeight;
  camera.position.set(0, 0, 0.1);
  camera.updateProjectionMatrix();
  renderer.setSize(
    document.documentElement.clientWidth,
    document.documentElement.clientHeight
  );
  document.querySelector(".container").querySelector("div").style.width =
    document.documentElement.clientWidth + "px";
  document.querySelector(".container").querySelector("div").style.height =
    document.documentElement.clientHeight + "px";
  render();
  window.location.reload();
}
function changeRight() {
  document.querySelector(".footer1").style.display = "none";
  document.querySelector(".changeRight").style.display = "none";
  document.querySelector(".footer2").style.display = "flex";
  document.querySelector(".changeLeft").style.display = "block";
}
function changeLeft() {
  document.querySelector(".footer2").style.display = "none";
  document.querySelector(".changeLeft").style.display = "none";
  document.querySelector(".footer1").style.display = "flex";
  document.querySelector(".changeRight").style.display = "block";
}
function changeDown() {
  document.querySelector(".side1").style.display = "none";
  document.querySelector(".changeDown").style.display = "none";
  document.querySelector(".side2").style.display = "flex";
  document.querySelector(".changeUp").style.display = "block";
}
function changeUp() {
  document.querySelector(".side2").style.display = "none";
  document.querySelector(".changeUp").style.display = "none";
  document.querySelector(".side1").style.display = "flex";
  document.querySelector(".changeDown").style.display = "block";
}
let phone = ref();
function getData(id) {
  axios({
    method: "get",
    url: `https://api.meijiawozhu.com/api/front/merchant/detail/${id}`,
  })
    .then((res) => {
      if (res.data.data.phone) {
        phone.value = res.data.data.phone;
        document.querySelectorAll("a").forEach((item) => {
          item.style.display = "flex";
        });
      }
    })
    .catch((err) => {
      console.log(err);
    });
}
function getId(id) {
  axios({
    method: "get",
    url: `https://api.meijiawozhu.com/api/front/product/detail/${id}`,
  })
    .then((res) => {
      if (res.data.data.productInfo.merId) {
        getData(res.data.data.productInfo.merId);
      }
    })
    .catch((err) => {
      console.log(err);
    });
}

let avatar = ref();
function getPhone(currentAgent) {
  // phone.value = arr[3].split("=")[1];
  phone.value = currentAgent.phone;
  avatar.value = currentAgent.avatar;
  document.querySelectorAll("a").forEach((item) => {
    item.style.display = "flex";
  });
}
function mousewheel(e) {
  if (e.wheelDelta) {
    //判断浏览器IE，谷歌滑轮事件
    if (e.wheelDelta > 0) {
      //当滑轮向上滚动时
      fov -= near < fov ? 1 : 0;
    }
    if (e.wheelDelta < 0) {
      //当滑轮向下滚动时
      fov += fov < far ? 1 : 0;
    }
  } else if (e.detail) {
    //Firefox滑轮事件
    if (e.detail > 0) {
      //当滑轮向上滚动时
      fov -= 5;
    }
    if (e.detail < 0) {
      //当滑轮向下滚动时
      fov += 5;
    }
  }
  camera.fov = fov;
  camera.updateProjectionMatrix();
  renderer.render(scene, camera);
}

let controls, str, arr, title, currentAgent;
onMounted(() => {
  if (navigator.userAgent.indexOf("AliApp") > -1) {
    document.querySelector(".footer1").style.display = "none";
    document.querySelector(".footer2").style.display = "none";
    document.querySelector(".footer3").style.display = "flex";
    document.querySelector(".changeLeft").style.display = "none";
    document.querySelector(".changeRight").style.display = "none";
  }
  getInfo(11);
  str = window.location.search.substr(1) || "";
  arr = str.split("&") || "";
  if (arr.length > 2) {
    title = decodeURIComponent(arr[2].split("=")[1]);
  }
  document.title = title || "vr";
  if (arr[0] && arr[0].indexOf("id") !== -1) {
    let id = arr[0].split("=")[1];
    getInfo(id);
  }
  if (arr[3]) {
    currentAgent = JSON.parse(decodeURIComponent(arr[3].split("=")[1]));
    console.log(currentAgent);
    getPhone(currentAgent);
  } else {
    if (arr[1] && arr[1].indexOf("storeId") !== -1) {
      let id = arr[1].split("=")[1];
      getData(id);
    }
    if (arr[1] && arr[1].indexOf("productId") !== -1) {
      let id = arr[1].split("=")[1];
      getId(id);
    }
  }
  window.addEventListener("resize", renderResize, false);
  window.addEventListener(
    "touchmove",
    function () {
      var p;
      if (camera.position.x < 0 && camera.position.z > 0) {
        p = -Math.tanh(camera.position.x / camera.position.z) * 90;
      }
      if (camera.position.x < 0 && camera.position.z < 0) {
        p = 180 - Math.tanh(camera.position.x / camera.position.z) * 90;
      }
      if (camera.position.x > 0 && camera.position.z < 0) {
        p = 180 - Math.tanh(camera.position.x / camera.position.z) * 90;
      }
      if (camera.position.x > 0 && camera.position.z > 0) {
        p = 360 - Math.tanh(camera.position.x / camera.position.z) * 90;
      }
      if (document.querySelector(".img1")) {
        document.querySelector(".img1").style.transform = `rotate(${p}deg)`;
      }
    },
    false
  );
  window.addEventListener("mousewheel", mousewheel, false);
  controls = new OrbitControls(camera, container.value);
  controls.enableZoom = false;
  controls.rotateSpeed = -1;
  container.value.appendChild(renderer.domElement);
  container.value.appendChild(labelRenderer.domElement);
  render();
});
</script>

<style>
* {
  margin: 0;
  padding: 0;
}

.container {
  height: 100vh;
  width: 100vw;
  background-color: #f0f0f0;
}

.laber_name {
  background-color: transparent;
  border: 0;
  font-size: 12px;
  text-align: center;
}

.circle {
  background-color: #d52d2d;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  text-align: center;
  line-height: 20px;
  color: #fff;
}

.content {
  /* width: 150px; */
  background-color: #fff;
  font-size: 12px;
  border-radius: 10px;
  padding: 5px 15px;
}

.img1 {
  position: absolute;
  top: 30px;
  left: 30px;
  transform-origin: center left;
}

.roam1,
.model1 {
  height: 35px;
  background-color: rgba(0, 0, 0, 0.3);
  position: absolute;
  top: 20px;
  display: flex;
  justify-content: space-around;
  padding: 0 5px;
  align-items: center;
  color: #fff;
  font-size: 16px;
}

.roam1 {
  width: 60px;
  right: 120px;
}

.model1 {
  width: 80px;
  right: 10px;
}

.explain1 {
  width: 60px;
  height: 60px;
  position: absolute;
  top: 80px;
  right: 5px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.explain1 .ai1 {
  width: 40px;
  height: 40px;
  text-align: center;
  line-height: 40px;
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 50%;
  font-size: 22px;
  margin-bottom: 3px;
}

.avatar {
  width: 40px;
  height: 40px;
  position: absolute;
  top: 155px;
  right: 15px;
  border-radius: 50%;
  overflow: hidden;
}

.avatar img {
  width: 40px;
  height: 40px;
}

.audio {
  position: absolute;
  top: 0;
  right: 0;
}

.footer1,
.footer2,
.footer3 {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 70px;
  background-color: transparent;
  display: flex;
  justify-content: space-around;
  align-items: center;
  color: #fff;
  padding: 0 20px 10px 0;
  box-sizing: border-box;
}

.footer3 {
  padding-right: 0;
  display: none;
}

.footer1 > div,
.footer2 > div,
.footer1 a,
.footer3 > div,
.footer3 a {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 25%;
}

.footer1 div img,
.footer1 a img {
  margin-bottom: 6px;
}

.footer2 div img {
  margin-top: 10px;
}

.footer2 {
  display: none;
  justify-content: flex-start;
  padding: 0 0 10px 20px;
}

.changeRight {
  position: absolute;
  bottom: 30px;
  right: 10px;
}

.changeLeft {
  display: none;
  position: absolute;
  bottom: 30px;
  left: 10px;
}

.side1,
.side2 {
  position: absolute;
  top: 0;
  right: 0;
  width: 80px;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  box-sizing: border-box;
  padding: 0 10px 20px 0;
  color: #fff;
}

.side2 {
  display: none;
  flex-direction: column;
  justify-content: flex-start;
  padding: 20px 10px 0 0;
}

.side1 > div,
.side2 > div,
.side2 > a {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 25%;
}

.side1 div img,
.side2 a img,
.side2 div img {
  margin-bottom: 6px;
}

.changeDown {
  position: absolute;
  bottom: 5px;
  right: 30px;
}

.changeUp {
  display: none;
  position: absolute;
  top: 5px;
  right: 30px;
}

.navbar-list {
  width: 100%;
  height: 80px;
  position: absolute;
  bottom: 80px;
  left: 0;
  overflow-x: scroll;
  overflow-y: hidden;
  box-sizing: border-box;
}

.navbar-list > ul {
  position: absolute;
  height: 80px;
  left: 0;
  display: flex;
  flex-flow: row nowrap;
  box-sizing: border-box;
}

.navbar-item {
  width: max-content;
  padding: 0 10px;
  box-sizing: border-box;
  height: 80px;
  display: flex;
  flex-flow: column nowrap;
  justify-content: space-around;
  align-items: center;
  cursor: pointer;
  color: #fff;
  font-size: 12px;
}

.navbar-item img {
  width: 100px;
}

.navbar-list::-webkit-scrollbar {
  display: none;
}

.load {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #000;
  color: #fff;
  font-size: 36px;
  text-align: center;
  padding-top: 50vh;
  z-index: 9;
  box-sizing: border-box;
}
</style>
