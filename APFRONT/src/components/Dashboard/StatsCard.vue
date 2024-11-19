<template>
    <div :class="`flex items-center p-4 rounded-lg shadow-lg text-white ${bgColor}`">
      <div class="text-3xl mr-4">{{ icon }}</div>
      <div>
        <h2 class="text-lg font-semibold">{{ title }}</h2>
        <p class="text-2xl font-bold">{{ value }}</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      title: String,
      value: String,
      icon: String,
      bgColor: String,
    },
  };
  </script>
  