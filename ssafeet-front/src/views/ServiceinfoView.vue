<template>
  <div class="service-info">
    <!-- 상단 이미지 추가 -->
    <img src="@/assets/ssafeet_logo.png" alt="SSAFEET 로고" class="logo" />
    <p>
      안녕하세요!<br /><br />
      싸핏은 SSAFY 학생들의 건강한 싸피 생활을 위한 앱으로,
      운동할 시간이 부족한 싸피 학생들이 매일 걷기를 통해 운동량을 보충할 수 있도록 도와주는 헬스케어 어플리케이션이에요.
    </p>
    <p>
      3000보는 하루 최소 걸음수입니다. 이는 70대 권장 걸음수에 해당해요.<br />
      5000보는 일상생활을 통해 걷게 되는 걸음수에 해당합니다.<br />
      7500보를 걸으면, 사망 위험률을 낮출 수 있습니다.<br />
      만 보까지 걷지 않아도 돼요. 7~8000보는 과학적으로 권장되는 하루 최대 걸음수입니다.
      따라서 8000보는 싸핏의 하루 최대 걸음수입니다.
    </p>
    <p>
      걸음수에 따라 포인트를 얻고, 포인트를 모아 싸피 제휴 브랜드의 기프티콘이나 할인쿠폰을 구매할 수 있어요.<br />
      3000보는 20p, 5000보는 30p, 7500보는 40p를 얻을 수 있고, 8000보까지 걷게 되면 추가로 10p를 얻을 수 있어요.
    </p>
    <p>
      싸핏은 싸피인들의 걷기 습관을 지속하기 위해 다양한 서비스를 제공하고 있어요.
    </p>
    <p>
      싸핏과 함께 건강한 싸피 생활을 시작해 볼까요?
    </p>
    <!-- 하단 오른쪽 이미지 추가 -->
    <img src="@/assets/character_1.png" alt="character1" class="character1" />
  </div>
</template>

<script>
export default {
  name: 'ServiceInfo',
};
</script>

<style scoped>
/* 브라우저 기본 여백 제거 */
html,
body {
  margin: 0;
  padding: 0;
}

/* 전체 배경 스타일 */
.service-info {
  padding: 30px;
  line-height: 1.6;
  background-color: #eef2f7; /* 연한 파란색 배경 */
  text-align: left;
  position: relative; /* 하단 이미지 위치를 위해 */
  min-height: 100vh; /* 페이지 전체 높이를 채움 */
  box-sizing: border-box; /* 패딩 포함 */
  font-family: "Nanum Gothic", sans-serif;
}

/* 로고 이미지 스타일 */
.logo {
  width: 150px; /* 이미지 너비 */
  height: auto; /* 비율 유지 */
  margin-bottom: 20px; /* 아래쪽 여백 */
}

/* 하단 이미지 스타일 */
.character1 {
  position: absolute; /* 위치 고정 */
  bottom: 40px; /* 하단에서 20px 위로 */
  right: 30px; /* 오른쪽에서 20px 왼쪽으로 */
  width: 80px; /* 이미지 크기 더 작게 조정 */
  height: auto; /* 비율 유지 */
  transform: rotate(10deg); /* 10도 기울이기 */
  transition: transform 0.3s ease; /* 부드러운 애니메이션 효과 */
}

/* 텍스트 스타일 */
h1 {
  font-size: 24px;
  font-weight: bold;
  color: #000000; /* 진한 회색 */
  margin-bottom: 20px;
}

p {
  font-size: 16px;
  font-weight: bold; /* 글씨 굵게 */
  color: #000000; /* 중간 회색 */
  margin: 10px;
}
</style>
