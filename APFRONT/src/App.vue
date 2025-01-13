<template>
<div id="app">
  <BaseNav v-if="showNav" />
  <div class="mg-4">
    <router-view />
  </div>
</div>
</template>

<script>
import { mapState } from 'vuex';
import BaseNav from './components/BaseNav.vue';

export default {
components: {
  BaseNav
},
computed: {
  showNav() {
    return this.$route.name !== 'login';
  }
}
};
</script>

<style>
#app {
  background-color: aliceblue;
  margin-bottom: 15vh;
}
</style>