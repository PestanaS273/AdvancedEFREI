<script setup>
import FormsTable from '../../components/Dashboard/FormsTable.vue';



</script>

<template>
    <h1 class="text-3xl font-bold text-gray-800 mb-6">Dashboard</h1>

    <FormsTable :data="forms" />
</template>

<script>
export default {
    components: { FormsTable },
    data() {
      return {
      forms: [
          { id: 1, student: "Sebastian", created: "2024-11-01" },
          { id: 2, student: "Adam", created: "2024-11-05" },
        ],
      };
  },
};


</script>


<style lang="scss" scoped>

</style>