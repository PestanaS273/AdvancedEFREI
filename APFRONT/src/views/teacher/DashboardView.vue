<script setup>
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
</script>

<template>
    <div class="p-6 bg-gray-100 min-h-screen">

        <!-- Título del Dashboard -->
        <h1 class="text-3xl font-bold text-gray-800 mb-6">{{ t('Dashboard') }}</h1>

        <!-- Sección de estadísticas -->
        <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">
            <StatsCard
                :title="t('Answer Percentage')"
                value="85%"
                icon="📊"
                bgColor="bg-green-500"
            />
            <StatsCard
                :title="t('Total Forms')"
                value="120"
                icon="📋"
                bgColor="bg-blue-500"
            />
            <StatsCard
                :title="t('Active Users')"
                value="350"
                icon="👥"
                bgColor="bg-purple-500"
            />
        </div>

        <!-- Tabla de encuestas -->
        <DataTable :data="surveys" />
    </div>
</template>

<script>
import StatsCard from "../../components/Dashboard/StatsCard.vue";
import DataTable from "../../components/Dashboard/DataTable.vue";



export default {
components: { StatsCard, DataTable },
data() {
    return {
        surveys: [
            { id: 1, title: "Encuesta de Satisfacción", responses: 150, created: "2024-11-01" },
            { id: 2, title: "Opinión sobre Productos", responses: 200, created: "2024-11-05" },
        ],
    };
    },
};
</script>

<style scoped>
</style>
    