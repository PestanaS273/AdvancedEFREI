<script setup>
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
</script>
<template>
    <div class= "lg:p-36 md:p-52 sm:20 p-8 w-full lg:w-1/2">
        <h1 class="text-2xl font-semibold mb-2">{{t('First Connection')}}</h1>
        <h2 class="font-semibold mb-5"> {{t('Use your EFREI Email')}} </h2>
        <form action="#" method="POST">
        <!-- Username Input -->
        <div class="mb-4">
            <label for="email" class="block text-gray-800">{{t('Email')}}</label>
            <input type="text" id="email" name="email" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autocomplete="off">
        </div>

        <!-- Login Button -->
        <button @click="ChangePassword" type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full">{{t('Check Email')}}</button>
        </form>
        <div class="mb-6">
            <button @click="handleClick"><p class="inline underline text-sm">{{t('Back to Login')}}</p></button>
        </div>
        <!-- Sign up  Link -->
        <!-----
        <div class="mt-6 text-green-500 text-center">
        <a href="#" class="hover:underline">Sign up Here</a>
        </div> ----->
    </div>

</template>

<script>
import apiClient from "../services/api";

export default {
name: 'FirstConnectionLogin',
data() {
return {
  email: "",
  password: "",
};
},
methods: {
  async login() {
    try {
      const response = await apiClient.post.login({
        email: this.email,
        password: this.password,
      })
      const user = response.data;
      console.log(user);
      this.$store.dispatch("login", user);
      this.$router.push("/dashboard");
    } catch (error) {
      console.error(error);
    }
  },
  handleClick() {
    this.$emit('change-component', 'LoginForm'); 
  },
  ChangePassword() {
      this.$emit('change-component', 'ChangePassword');
  }
}
}
</script>

<style lang="scss" scoped>

</style>