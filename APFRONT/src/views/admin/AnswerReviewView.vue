<script setup>
import AnswerTable from '../../components/Dashboard/AnswerTable.vue';




</script>

<template>
    <h1 class="text-3xl mx-4 font-bold text-gray-800 mb-6">Class</h1>

    <AnswerTable :data="forms" />
</template>

<script>
export default {
    components: { AnswerTable },
    data() {
      return {
      forms: [
          { id: 1, question: "Points", answer: "5"  },
          { id: 2, question: "Grade", answer: "4" },
        ],
      };
  },

};


</script>


<style lang="scss" scoped>

</style>