<template>
  <!-- component -->
  <div class="bg-sky-100 flex justify-center items-center h-screen overflow-hidden">
    <!-- Left: Image -->
    <div class="w-1/2 h-screen hidden lg:block">
      <img src="../assets/ecole-ingenieur-efrei.jpg" alt="Placeholder Image" class="object-cover w-full h-full">
    </div>
    <!-- Right: Login Form -->
    <div class= "lg:p-36 md:p-52 sm:20 p-8 w-full lg:w-1/2">
      <h1 class="text-2xl font-semibold mb-2">Login</h1>
      <h2 class="font-semibold mb-5"> Use your EFREI account </h2>
      <form action="#" method="POST">
        <!-- Username Input -->
        <div class="mb-4">
          <label for="username" class="block text-gray-800">Username</label>
          <input type="text" id="username" name="username" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autocomplete="off">
        </div>
        <!-- Password Input -->
        <div class="mb-4">
          <label for="password" class="block text-gray-800">Password</label>
          <input type="password" id="password" name="password" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autocomplete="off">
        </div>
        <div class="mb-6">
          <span class="text-sm mr-2 "><p class="inline">Forgot Password?</p></span>
          <span class="text-sm"> <p class="underline inline">Please contact +33 188 289 250</p></span>
        </div>
        <!-- Login Button -->
        <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full">Login</button>
      </form>
      <!-- Sign up  Link -->
      <!-----
     <div class="mt-6 text-green-500 text-center">
       <a href="#" class="hover:underline">Sign up Here</a>
     </div> ----->
    </div>
  </div>

</template>

<script>
import apiClient from "../services/api";

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
    }
  }
}

</script>