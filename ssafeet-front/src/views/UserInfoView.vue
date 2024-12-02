<template>
  <div class="user-info">
    <h1>회원정보</h1>
    <div class="profile">
      <img src="/src/assets/user-icon.png" alt="프로필 사진" />
      <button class="profile-edit-btn">프로필 사진 수정</button>
    </div>
    <div class="info">
      <p>서울 캠퍼스</p>
      <p>김싸피</p>
    </div>
    <div class="actions">
      <button>비밀번호 변경</button>
      <button>휴대폰 번호 변경</button>
      <button @click="handleAccountDeletion">회원탈퇴</button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

// useRouter should be inside the setup function
const router = useRouter();

const handleAccountDeletion = () => {
  // Remove the token from localStorage
  localStorage.removeItem('access-token');

  // Optionally, you can redirect the user after account deletion
  router.push('/signup');  // Or redirect to any other page you want
};
</script>

<style scoped>
/* Your existing styles */
.user-info {
  text-align: center;
  padding: 20px;
  box-sizing: border-box;
}

h1 {
  font-size: 10px; /* 글씨 크기 줄이기 */
  font-weight: bold;
  color: #333;
  position: absolute;
  top: 20px;
  left: 20px;
  margin: 0;
}

/* 프로필 사진과 수정 버튼 스타일 */
.profile {
  display: flex;
  flex-direction: column;
  justify-content: center; /* 세로 방향으로 가운데 정렬 */
  align-items: flex-end; /* 오른쪽 정렬 */
  margin-top: 40px;
  position: relative;
}

.profile img {
  border-radius: 50%;
  margin-bottom: -150px; /* 프로필 사진과 버튼 사이 간격 조정 */
  width: 300px; /* 프로필 사진 크기 더 크게 */
  height: 300px; /* 프로필 사진 크기 더 크게 */
  margin-right: 10px; /* 프로필 사진을 왼쪽으로 이동 */
}

.profile .profile-edit-btn {
  margin-top: 0px; /* 프로필 사진 아래로 더 떨어지게 */
  padding: 5px 15px; /* 버튼 크기 조정 */
  font-size: 12px; /* 글씨 크기 조정 */
  cursor: pointer;
  border-radius: 20px; /* 버튼 둥글게 */
  background-color: #D9D9D9;
  color: #000000;
  border: none; /* 테두리 없애기 */
  box-shadow: none; /* 그림자 없애기 */
  transition: all 0.3s ease;
}

/* 프로필 수정 버튼 hover 및 active 효과 */
.profile .profile-edit-btn:hover {
  background-color: #4e4e4e; /* hover 시 배경색 변경 */
  color: #ffffff; /* 글자색 흰색으로 변경 */
}

.profile .profile-edit-btn:active {
  background-color: #3a3a3a; /* active 시 배경색 변경 */
  transform: scale(0.95); /* 클릭 시 크기 줄어드는 효과 */
}

/* 사용자 정보 텍스트 스타일 */
.info {
  font-weight: bold; /* 굵은 글씨 */
  margin-top: 20px; /* 사용자 정보와 프로필 사진 사이의 간격 추가 */
}

.info p {
  margin: 10px 0; /* 문구 간의 간격 늘리기 */
}

/* 액션 버튼 스타일 (세로로 배치) */
.actions {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.actions button {
  margin: 10px 0; /* 버튼 간의 간격 */
  padding: 12px 20px; /* 버튼 크기 */
  font-size: 15px; /* 글씨 크기 */
  cursor: pointer;
  border: none;
  border-radius: 20px; /* 둥글둥글한 버튼 */
  background-color: #D9D9D9; /* 버튼 배경색 */
  color: #000000; /* 텍스트 색 */
  transition: all 0.3s ease;
  width: 170px; /* 버튼 너비 고정 */
}

/* 버튼 hover 및 active 효과 */
.actions button:hover {
  background-color: #4e4e4e;
  transform: scale(1.05);
  color: #ffffff; /* 글자색 흰색으로 변경 */
}

.actions button:active {
  background-color: #3a3a3a;
  transform: scale(0.95);
  color: #ffffff; /* 글자색 흰색으로 변경 */
}
</style>
