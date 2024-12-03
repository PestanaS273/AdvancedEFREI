<template>
  <div class="bg-sky-100 flex justify-center items-center h-screen overflow-hidden">
    <!-- Left: Image -->
    <div class="w-1/2 h-screen hidden lg:block">
      <img src="../assets/ecole-ingenieur-efrei.jpg" alt="Placeholder Image" class="object-cover w-full h-full">
    </div>
    <!-- Right: Login Form -->
    <div class="lg:p-36 md:p-52 sm:20 p-8 w-full lg:w-1/2">
      <h1 class="text-2xl font-semibold mb-2">Login</h1>
      <h2 class="font-semibold mb-5"> Use your EFREI account </h2>
      <form @submit.prevent="login">
        <!-- Username Input -->
        <div class="mb-4">
          <label for="email" class="block text-gray-800">Email</label>
          <input
            v-model="email"
            type="email"
            id="email"
            name="email"
            class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500"
            autocomplete="off"
            required
          />
        </div>
        <!-- Password Input -->
        <div class="mb-4">
          <label for="password" class="block text-gray-800">Password</label>
          <input
            v-model="password"
            type="password"
            id="password"
            name="password"
            class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500"
            autocomplete="off"
            required
          />
        </div>
        <div class="mb-6">
          <span class="text-sm mr-2"><p class="inline">Forgot Password?</p></span>
          <span class="text-sm"><p class="underline inline">Please contact +33 188 289 250</p></span>
        </div>
        <!-- Login Button -->
        <button
          type="submit"
          class="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full"
        >
          Login
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import ConnexionService from "../services/autherization.services";
import * as jwt from 'jwt-decode';


export default {
  data() {
    return {
      email: "",
      password: "",
    };
  },
  
  methods: {
    async login() {
      try {
        // Appel à l'API de ConnexionService
        console.log(this.email, this.password);
        const response = await ConnexionService.loginAny({
          email: this.email,
          password: this.password,
        });

        // Récupérer le JWT depuis les en-têtes
        const jwt = response.headers.authorization;
        console.log("jwt: ", jwt)
        if (jwt) {
          ConnexionService.saveToken(jwt);

          // Affichage des informations utilisateur
          console.log("Email:", ConnexionService.email);
          console.log("Rôles:", ConnexionService.roles);

          // Rediriger selon le rôle
          if (ConnexionService.isAdmin()) {
            this.router.push("/gestionUtilisateur");
          } else if (ConnexionService.isEtudiant()) {
            this.$router.push("/");
          } else {
            this.router.push("/catalogue");
          }
        }
      } catch (error) {
        console.error("Erreur de connexion :", error);
        this.toast.error("Le mot de passe ou l'email est incorrect !", { timeout: 5000 });
      }
    },
  },
};
</script>

<style scoped>
/* Ajoutez vos styles ici */
</style>
