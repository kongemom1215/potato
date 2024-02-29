// 큐브가 회전
import {GLTFLoader} from 'GLTFLoader';
import * as THREE from 'three';

let scene = new THREE.Scene();

let camera = new THREE.PerspectiveCamera(50,1); // 카메라 가져오기 (PerspectiveCamera:원근법 적용, OrthopraphicCamera:원근법 무시)
camera.position.set(0,0,5);

scene.background = new THREE.Color('white'); //배경
//let light = new THREE.DirectionalLight(0xffffff, 10);

let ambientLight = new THREE.AmbientLight('white', 0.52);
scene.add(ambientLight); //조명
let light = new THREE.DirectionalLight('white', 0.52);
scene.add(light); //조명

// 새로운 빈 객체 생성
let container = new THREE.Object3D();
scene.add(container); // Scene에 추가

// Scene에 추가할 객체들을 만듭니다. 이 예시에서는 큐브를 생성합니다.
let geometry = new THREE.BoxGeometry();
let material = new THREE.MeshBasicMaterial({ color: 0x00ff00 });
let cube = new THREE.Mesh(geometry, material);
container.add(cube); // container에 추가

// 렌더러 생성
let renderer = new THREE.WebGLRenderer({
    canvas : document.querySelector('#canvas'), //랜더링해줄 위치
    antialias : true // 계단현상 개선
});
renderer.outputEncoding = THREE.sRGBEncoding;
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);


// 렌더링 루프
function animate() {
    requestAnimationFrame(animate);

    // container를 중심으로 회전시킵니다.
    container.rotation.y += 0.01; // 회전 속도 조절

    // container를 시점에 따라 렌더링합니다.
    renderer.render(scene, camera);
}
animate();
