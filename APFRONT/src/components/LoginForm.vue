<script setup>
import { useI18n } from 'vue-i18n'
import { useStore } from 'vuex';

const { t } = useI18n()
const store = useStore();
</script>


<template>
        <div class= "lg:p-36 md:p-52 sm:20 p-8 w-full lg:w-1/2">
            <h1 class="text-2xl font-semibold mb-2">{{ t('Login') }}</h1>
            <h2 class="font-semibold mb-5"> {{t('Use your EFREI account')}} </h2>
            <form @submit.prevent="login">
            <!-- Username Input -->
            <div class="mb-4">
                <label for="username" class="block text-gray-800">{{t('Email')}}</label>
                <input
                    v-model="email" 
                    type="text" id="email" name="email" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" >
            </div>
            <!-- Password Input -->
            <div class="mb-4">
                <label for="password" class="block text-gray-800">{{t('Password')}}</label>
                <input 
                    v-model="password"
                    type="password" id="password" name="password" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" 
                    >
            </div>
            <div class="mb-6">
                <span class="text-sm mr-2 "><p class="inline">{{t('Forgot Password?')}}</p></span>
                <span class="text-sm"> <p class="">{{t('Please contact')}} +33 188 289 250</p></span>
            </div>

            <!-- Login Button -->
            <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full">{{t('Login')}}</button>
            </form>
            <div class="mb-6">
                <button @click="handleClick"><span><p class="inline underline text-sm">{{t('First Connection?')}}</p></span></button>

            </div>

        </div>

</template>

<script>
import ConnexionService from "../services/autherization.services";


export default {
    name: 'LoginForm',
    data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    async login() {
      try {
        const response = await ConnexionService.loginAny({
          email: this.email,
          password: this.password,
        });

        const jwt = response.headers.authorization;
        if (jwt) {
          ConnexionService.saveToken(jwt);
          this.$store.dispatch('user/login', {
            email: ConnexionService.email,
            roles: ConnexionService.roles,
          });
          console.log("Connexion réussie");


          this.$router.push("/dashboard");
        }
      } catch (error) {
        console.error("Erreur de connexion :", error);
        this.toast.error("Le mot de passe ou l'email est incorrect !", { timeout: 5000 });
      }
    },
    handleClick() {
      this.$emit('change-component', 'FirstConnectionLogin'); 
    },
  },
}

</script>

<style lang="scss" scoped>

</style>