<script setup>
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
</script>
<template>
    <div class= "lg:p-36 md:p-52 sm:20 p-8 w-full lg:w-1/2">
        <h1 class="text-2xl font-semibold mb-2">{{t('Change Password')}}</h1>
        <h2 class="font-semibold mb-5"> {{t('Update your passeword')}} </h2>
        <form action="#" method="POST">
        <!-- Username Input -->
        <div class="mb-4">
            <label for="newPassword" class="block text-gray-800">{{t('New password')}}</label>
            <input type="text" id="newPassword" name="newPassword" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autocomplete="off">
        </div>
        <div class="mb-4">
            <label for="confirmPassword" class="block text-gray-800">{{t('Confirm password')}}</label>
            <input type="text" id="confirmPassword" name="confirmPassword" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autocomplete="off">
        </div>
        <!-- Login Button -->
        <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full">{{t('Change Password')}}</button>
        </form>

    </div>

</template>

<script>
import apiClient from "../services/api";

export default {
name: 'ChangePassword',
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
},
}

</script>

<style lang="scss" scoped>

</style>