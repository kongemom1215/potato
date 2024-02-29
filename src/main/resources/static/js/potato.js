import {GLTFLoader} from 'GLTFLoader';
import * as THREE from 'three';
//import { GLTFLoader } from 'three/addons/loaders/GLTFLoader.js';
//import * as THREE from 'https://unpkg.com/three/build/three.module.js';

let scene = new THREE.Scene();
let renderer = new THREE.WebGLRenderer({
    canvas : document.querySelector('#canvas'), //랜더링해줄 위치
    antialias : true // 계단현상 개선
});

renderer.outputEncoding = THREE.sRGBEncoding;

let camera = new THREE.PerspectiveCamera(50,1); // 카메라 가져오기 (PerspectiveCamera:원근법 적용, OrthopraphicCamera:원근법 무시)
camera.position.set(0,0,5);
//scene.background = new THREE.Color("white");
scene.background = new THREE.Color("rgb(248, 249, 250)"); //배경
//let light = new THREE.DirectionalLight(0xffffff, 10);

let ambientLight = new THREE.AmbientLight('white', 0.52);
scene.add(ambientLight); //조명
let light = new THREE.DirectionalLight('white', 0.52);
scene.add(light); //조명

let loader = new GLTFLoader();

loader.load('/potato/scene.gltf', function(gltf){ //gltf 파일 가져오기
    scene.add(gltf.scene); //장면을 넣고

    function animate(){
        requestAnimationFrame(animate); //1초에 60번
        gltf.scene.rotation.y += 0.007;
        renderer.render(scene, camera); //장면을 렌더링
    }

    animate();
});