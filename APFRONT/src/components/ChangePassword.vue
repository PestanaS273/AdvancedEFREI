<script setup>
import { useI18n } from 'vue-i18n'
import { useStore } from 'vuex';

const { t } = useI18n()
const store = useStore();
</script>

<template>
  <div class="lg:p-36 md:p-52 sm:20 p-8 w-full lg:w-1/2">
    <h1 class="text-2xl font-semibold mb-2">{{ t('Change Password') }}</h1>
    <h2 class="font-semibold mb-5">{{ t('Update your password') }}</h2>
    <form @submit.prevent="changePassword">
      <!-- New Password Input -->
      <div class="mb-4">
        <label for="newPassword" class="block text-gray-800">{{ t('New password') }}</label>
        <input v-model="newPassword" type="password" id="newPassword" name="newPassword" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autocomplete="off">
      </div>
      <!-- Confirm Password Input -->
      <div class="mb-4">
        <label for="confirmPassword" class="block text-gray-800">{{ t('Confirm password') }}</label>
        <input v-model="confirmPassword" type="password" id="confirmPassword" name="confirmPassword" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autocomplete="off">
      </div>
      <!-- Change Password Button -->
      <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full">{{ t('Change Password') }}</button>
    </form>
  </div>
</template>

<script>
import utilisateurServices from '../services/utilisateur.services'

export default {
  name: 'ChangePassword',
  data() {
    return {
      newPassword: "",
      confirmPassword: ""
    }
  },
  methods: {
    async changePassword(event) {
      event.preventDefault()
      if (this.newPassword !== this.confirmPassword) {
        console.error("Passwords do not match")
        return
      }

      const email = this.$store.getters['user/getUser'].email
      console.log(email)
      const user = {
        email: email,
        password: this.newPassword
      }
      console.log(user)
      try {
        const response = await utilisateurServices.inscrire(user)
        console.log(response)
        this.$emit('change-component', 'LoginForm')
      } catch (error) {
        console.error(error)
      }
    },
    // handleClick() {
    //   this.$emit('change-component', 'LoginForm'); 
    // },
  }
}
</script>

<style lang="scss" scoped>
</style>