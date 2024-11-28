<template>
  <!-- component -->
  <div class="bg-sky-100 flex justify-center items-center h-screen overflow-hidden">
    <!-- Left: Image -->
    <div class="w-1/2 h-screen hidden lg:block">
      <img src="../assets/ecole-ingenieur-efrei.jpg" alt="Placeholder Image" class="object-cover w-full h-full">
    </div>
      <component :is="currentComponent" @change-component="changeComponent" />
  </div>
  <div class="absolute top-0 right-0">  
    <LanguageSwitcher />
  </div>

</template>

<script>
import LoginForm from "../components/LoginForm.vue";
import FirstConnectionLogin from "../components/FirstConnectionLogin.vue";
import LanguageSwitcher from "../components/LanguageSwitcher.vue";
import ChangePassword from "../components/ChangePassword.vue";

export default {
  components: {
    LoginForm,
    FirstConnectionLogin,
    LanguageSwitcher,
    ChangePassword,
  },
  data() {
    return {
      currentComponent: 'LoginForm',

    };
  },
  methods: {
    changeComponent(componentName) {
      this.currentComponent = componentName;
    },
  },
};
</script>