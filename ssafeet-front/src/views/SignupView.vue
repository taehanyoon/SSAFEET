<template>
  <div class="registration-wizard">
    <div v-for="step in visibleSteps" :key="step" class="form-step" :class="{'active-step': currentStep === step && currentStep < 6, 'no-border': currentStep >= 6}">
      <template v-if="step === 1">
        <h2 v-if="currentStep === 1">휴대폰 번호를 알려주세요</h2>
        <h6>휴대폰 번호</h6>
        <input
          type="tel"
          :value="formattedPhone"
          @input="handlePhoneInput"
          maxlength="13"
          :disabled="currentStep > 6 && currentStep > 1"
        />
      </template>

      <template v-else-if="step === 2">
        <h2 v-if="currentStep === 2">통신사를 알려주세요</h2>
        <h6>통신사</h6>
        <select v-model="formData.carrier" :disabled="currentStep > 6 && currentStep > 2">
          <option value="" disabled>통신사를 선택해주세요</option>
          <option v-for="carrier in carriers" :key="carrier" :value="carrier">{{ carrier }}</option>
        </select>
      </template>

      <template v-else-if="step === 3">
        <h2 v-if="currentStep === 3">이름을 알려주세요</h2>
        <h6>이름</h6>
        <input
          type="text"
          v-model="formData.name"
          :disabled="currentStep > 6 && currentStep > 3"
        />
      </template>

      <template v-else-if="step === 4">
        <h2 v-if="currentStep === 4">캠퍼스를 알려주세요</h2>
        <h6>캠퍼스</h6>
        <select v-model="formData.campus" :disabled="currentStep > 6 && currentStep > 4">
          <option value="" disabled>캠퍼스를 선택해주세요</option>
          <option v-for="campus in campuses" :key="campus" :value="campus">{{ campus }}</option>
        </select>
      </template>

      <template v-else-if="step === 5">
        <h2 v-if="currentStep === 5">학번을 알려주세요</h2>
        <h6>학번</h6>
        <input
          type="number"
          v-model="formData.studentId"
          ref="studentIdInput"
          :disabled="currentStep > 6 && currentStep > 5"
        />
      </template>

      <template v-else-if="step === 6">
        <h2 v-if="currentStep === 6">문자로 받은 인증번호 6자리를 입력해주세요</h2>
        <input
          type="tel"
          v-model="formData.verificationCode"
          maxlength="6"
          class="hidden-input"
          :disabled="currentStep > 6"
        />
        
        <div class="verification-code-container">
          <input
            v-for="(digit, index) in 6"
            :key="index"
            type="number"
            maxlength="1"
            :value="formData.verificationCode[index] || ''"
            :class="{'active': formData.verificationCode[index] !== ''}"
            class="verification-code-box"
            :disabled="true"
            :style="{'border-color': formData.verificationCode[index] ? '#007bff' : '#ddd'}"
          />
        </div>
      </template>

      <template v-else-if="step === 7">
        <h4 v-if="currentStep === 7">비밀번호를 입력해주세요</h4>
        <input
          type="tel"
          v-model="formData.password"
          maxlength="6"
          class="hidden-input"
          :disabled="currentStep > 7"
          ref="hiddenInput"
        />
        
        <div class="password-container">
          <input
            v-for="(digit, index) in 6"
            :key="index"
            :class="{'active': formData.password[index] !== ''}"
            class="password-box"
            :disabled="true"
            :style="{'background-color': formData.password[index] ? '#92b0ce' : '#1b3855'}"
          />
        </div>
      </template>

      <template v-else-if="step === 8">
        <h4 v-if="currentStep === 8">비밀번호를 한 번 더 입력해주세요</h4>
        <input
          type="tel"
          v-model="formData.confirmPassword"
          maxlength="6"
          class="hidden-input"
          :disabled="currentStep > 8"
          ref="hiddenInput"
        />
        
        <div class="password-container">
          <input
            v-for="(digit, index) in 6"
            :key="index"
            :class="{'active': formData.confirmPassword[index] !== ''}"
            class="password-box"
            :disabled="true"
            :style="{'background-color': formData.confirmPassword[index] ? '#92b0ce' : '#1b3855'}"
          />
        </div>
      </template>

      <template v-else-if="step === 9">
        <div class="checkmark-container">
          <div class="checkmark">
            <div class="checkmark-line" :class="{ 'draw': isChecked }"></div>
          </div>
        </div>
        <h4 v-if="currentStep === 9" style="font-size: 32px;">회원가입 완료!</h4>
      </template>
    </div>

    <button
      class="next-button"
      v-if="currentStep < 7"
      @click="nextStep"
      :disabled="!isStepValid"
    >
      다음
    </button>
  </div>
</template>

<script>
import { ref, computed, watch, nextTick } from "vue";
import { useUserStore } from "@/stores/user";

export default {
  setup() {
    const currentStep = ref(1);
    const isChecked = ref(false);

    const formData = ref({
      phone: "",
      carrier: "",
      name: "",
      campus: "",
      studentId: "",
      verificationCode: "",
      password: "",
      confirmPassword: "",
    });

    const carriers = [
      "SKT",
      "KT",
      "LG U+",
      "SKT 알뜰폰",
      "KT 알뜰폰",
      "LG U+ 알뜰폰",
    ];

    const campuses = ["서울", "대전", "구미", "광주", "부울경"];

    const store = useUserStore();

    const visibleSteps = computed(() => {
      if (currentStep.value < 6) {
        return Array.from({ length: currentStep.value }, (_, i) => currentStep.value - i);
      } else {
        return [currentStep.value];
      }
    });

    const formattedPhone = computed(() => {
      const phone = formData.value.phone.replace(/\D/g, "");
      if (phone.length <= 3) {
        return phone;
      } else if (phone.length <= 7) {
        return `${phone.slice(0, 3)}-${phone.slice(3)}`;
      } else {
        return `${phone.slice(0, 3)}-${phone.slice(3, 7)}-${phone.slice(7, 11)}`;
      }
    });

    const isStepValid = computed(() => {
      for (let step = 1; step <= currentStep.value; step++) {
        if (!validateStep(step)) {
          return false;
        }
      }
      return true;
    });

    const validateStep = (step) => {
      switch (step) {
        case 1:
          return formData.value.phone && formData.value.phone.length === 11;
        case 2:
          return formData.value.carrier && formData.value.carrier.trim() !== "";
        case 3:
          return formData.value.name && formData.value.name.trim() !== "";
        case 4:
          return formData.value.campus && formData.value.campus.trim() !== "";
        case 5:
          return (
            formData.value.studentId &&
            formData.value.studentId.toString().length > 0
          );
        case 6:
          return (
            formData.value.verificationCode &&
            formData.value.verificationCode.length === 6
          );
        case 7:
          return (
            formData.value.password && formData.value.password.length === 6
          );
        case 8:
          return (
            formData.value.password &&
            formData.value.password === formData.value.confirmPassword
          );
        default:
          return true;
      }
    };

    const handlePhoneInput = (event) => {
      const rawInput = event.target.value.replace(/\D/g, "");
      formData.value.phone = rawInput;

      if (rawInput.length === 11) {
        event.target.blur();
      }
    };

    const createUser = () => {
      const user = {
        id: formData.value.studentId,
        phone: formData.value.phone,
        name: formData.value.name,
        campus: formData.value.campus,
        password: formData.value.password,
      };
      store.createUser(user);
      
      setTimeout(() => {
        store.userLogin(user.phone, user.password);
      }, 200)
    };

    const nextStep = () => {
      if (isStepValid.value) {
        currentStep.value++;
      }
    };

    watch(currentStep, (newStep) => {
      if (newStep === 9) {
        setTimeout(() => {
          isChecked.value = true;
        }, 100);
        createUser();
        setTimeout(() => {
          window.location.href = "/";
        }, 3000);
      }
      if (newStep >= 7) {
        document.body.style.backgroundColor = "#3570a9";
      } else {
        document.body.style.backgroundColor = "";
      }
    });

    watch(
      () => formData.value.password,
      (newPassword) => {
        if (newPassword.length === 6) {
          nextStep();
        }
      }
    );

    watch(
      () => formData.value.confirmPassword,
      (newPassword) => {
        if (newPassword.length === 6) {
          if (formData.value.password === formData.value.confirmPassword) {
            nextStep();
          } else {
            formData.value.password = "";
            formData.value.confirmPassword = "";
            currentStep.value = 7;
          }
        }
      }
    );

    return {
      currentStep,
      formData,
      carriers,
      campuses,
      visibleSteps,
      isStepValid,
      formattedPhone,
      isChecked,
      nextStep,
      handlePhoneInput,
    };
  },
};
</script>

<style scoped>
.registration-wizard {
  font-family: "Nanum Gothic", sans-serif;
  max-width: 400px;
  margin: 20px auto;
  padding-top: 10%;
  padding-bottom: 60px;
}
.form-step {
  margin-bottom: 15px;
  border-bottom: 3px solid #ddd;
  padding-bottom: 10px;
}
h2 {
  font-size: 27px;
  margin-bottom: 10px;
  word-break: keep-all;
}
h4 {
  color: white;
  text-align: center;
}
h6 {
  margin-top: 30px;
}
input,
select {
  width: 100%;
  margin-bottom: -10px;
  border: none;
  background: none;
  font-size: 24px;
}
input:disabled,
select:disabled {
  background-color: #f0f0f0;
}
button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
.next-button {
  position: fixed;
  width: 75%;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  padding: 12px 24px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  z-index: 1000;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
.next-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
.checkmark {
  font-size: 50px;
  color: #4caf50;
  text-align: center;
}
.active-step h6 {
  color: #007bff;
}
/* .active-step input, .active-step select {
  color: #007bff;
} */
.form-step.active-step {
  border-bottom: 3px solid #007bff;
}
.no-border {
  border-bottom: 0px;
}
.hidden-input {
  position: absolute;
  opacity: 0;
  width: 90%;
  height: 30%;
}
.verification-code-container {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-top: 10px;
}
.verification-code-box {
  width: 40px;
  height: 50px;
  text-align: center;
  font-size: 24px;
  color: #007bff;
  border: 2px solid #ddd;
  border-radius: 8px;
  margin: 5px;
  margin-top: 20%;
}
.verification-code-box:focus {
  border-color: #007bff;
  outline: none;
}
.verification-code-box.active {
  border-color: #007bff;
  background-color: #e8f0fe;
}
.verification-code-box[disabled] {
  background-color: #f0f0f0;
}
.password-container {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-top: 10px;
}
.password-box {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin: 10px;
  margin-top: 20%;
}




.checkmark-container {
  display: flex;
  align-items: center;
  flex-direction: column;
}

.checkmark {
  width: 100px;
  height: 100px;
  border: 5px solid white;
  border-radius: 50%;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.checkmark-line {
  content: '';
  position: absolute;
  width: 20px;
  height: 50px;
  border: 5px solid white;
  border-top: none;
  border-right: none;
  transform: rotate(-45deg);
  opacity: 0;
  transform-origin: center center;
  transition: transform 1s ease, opacity 0.5s ease;
}

.checkmark-line.draw {
  opacity: 1;
  transform: rotate(45deg) scaleX(-1) scaleY(1);
}
</style>
