<template>
    <nav class="bg-gray-100 p-3 rounded-md w-full">
        <ol class="list-reset flex">
            <li v-for="(crumb, index) in breadcrumbs" :key="index" class="flex items-center">
                <RouterLink
                v-if="index < breadcrumbs.length - 1"
                :to="crumb.path"
                class="text-blue-600 hover:text-blue-700"
                >
                {{ crumb.name }}
                </RouterLink>
                <span v-else class="text-gray-500">{{ crumb.name }}</span>
                <span v-if="index < breadcrumbs.length - 1" class="mx-2">/</span>
            </li>
        </ol>
    </nav>
</template>

<script>
export default {
props: {
    breadcrumbs: {
    type: Array,
    required: true,
    },
},
};
</script>

<style scoped>
nav {
margin-bottom: 1rem;
}
</style>