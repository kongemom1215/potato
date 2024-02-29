// 발칙한 감자 버전
import { GLTFLoader } from 'GLTFLoader';
import * as THREE from 'three';

let scene = new THREE.Scene();
let renderer = new THREE.WebGLRenderer({
    canvas: document.querySelector('#canvas'),
    antialias: true
});

renderer.outputEncoding = THREE.sRGBEncoding;

let camera = new THREE.PerspectiveCamera(50, 1); // 카메라 가져오기 (PerspectiveCamera:원근법 적용, OrthopraphicCamera:원근법 무시)
camera.position.set(0, 0, 5);

scene.background = new THREE.Color('white'); //배경

let ambientLight = new THREE.AmbientLight('white', 0.52);
scene.add(ambientLight); //조명
let light = new THREE.DirectionalLight('white', 0.52);
scene.add(light); //조명

let loader = new GLTFLoader();

let rotationSpeed = 0.0001; // 회전 속도
let maxRotationSpeed = 0.5; // 최대 회전 속도

loader.load('/potato/scene.gltf', function (gltf) { //gltf 파일 가져오기
    let model = gltf.scene;
    scene.add(model); //장면을 넣고

    function animate() {
        requestAnimationFrame(animate); //1초에 60번

        // 현재 회전 속도가 최대 회전 속도를 넘지 않도록 조절
        if (rotationSpeed < maxRotationSpeed) {
            rotationSpeed += 0.0001; // 회전 속도에 가속도 추가
        }

        // 모델 회전
        gltf.scene.rotation.y += rotationSpeed;

        renderer.render(scene, camera); //장면을 렌더링
    }

    animate();
});