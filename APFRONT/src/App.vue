<template>
<div id="app">
  <BaseNav v-if="showNav" />
  <router-view />
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
</style>