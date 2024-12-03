<script setup>
import { useI18n } from 'vue-i18n'
import { useStore } from 'vuex';

const { t } = useI18n()
const store = useStore();
</script>

<template>
  <div class="lg:p-36 md:p-52 sm:20 p-8 w-full lg:w-1/2">
    <h1 class="text-2xl font-semibold mb-2">{{ t('First Connection') }}</h1>
    <h2 class="font-semibold mb-5">{{ t('Use your EFREI Email') }}</h2>
    <form @submit.prevent="ChangePassword">
      <!-- Username Input -->
      <div class="mb-4">
        <label for="email" class="block text-gray-800">{{ t('Email') }}</label>
        <input v-model="email" type="text" id="email" name="email" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autocomplete="off">
      </div>

      <!-- Login Button -->
      <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full">{{ t('Check Email') }}</button>
    </form>
    <div class="mb-6">
      <button @click="handleClick"><p class="inline underline text-sm">{{ t('Back to Login') }}</p></button>
    </div>
  </div>
</template>

<script>

import utilisateurServices from '../services/utilisateur.services';

export default {
  name: 'FirstConnectionLogin',
  data() {
    return {
      email: ""
    }
  },
  setup() {

  },
  methods: {
    async checkEmailExist() {
      try {
        console.log(this.email)
        const response = await utilisateurServices.checkEmailExist(this.email)
        console.log(response)
        return response
      } catch (error) {
        console.error(error)
        return false
      }
    },
    handleClick() {
      this.$emit('change-component', 'LoginForm')
    },
    async ChangePassword(event) {
      event.preventDefault()
      const emailExists = await this.checkEmailExist()  
      if (emailExists) {
        this.$store.dispatch('user/login', {
            email: this.email,
          });
        this.$emit('change-component', 'ChangePassword')
      } else {
        console.error("Email does not exist")
      }
    }
  }
}
</script>

<style lang="scss" scoped>
</style>