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
                :value=totalForms
                icon="📋"
                bgColor="bg-blue-500"
            />
            <StatsCard
                :title="t('Active Users')"
                :value=activeUsers
                icon="👥"
                bgColor="bg-purple-500"
            />
        </div>

        <!-- Tabla de encuestas -->
        <DataTable :data="surveys" />
    </div>

    <AddForm />
    <AddUser />
</template>

<script>
import StatsCard from "../../components/Dashboard/StatsCard.vue";
import DataTable from "../../components/Dashboard/DataTable.vue";
import AddForm from '../../components/Dashboard/AddForm.vue';
import AddUser from '../../components/Dashboard/AddUser.vue';
import utilisateurServices from '../../services/utilisateur.services';
import formServices from '../../services/form.services';




export default {
components: { StatsCard, DataTable, AddForm },
data() {
    return {
        surveys: [
            // { id: 1, title: "DevOps", responses: 150, created: "2024-11-01" },
            // { id: 2, title: "Advanced Programming", responses: 200, created: "2024-11-05" },
            // { id: 3, title: "Rest APIs", responses: 150, created: "2024-11-01" },
            // { id: 4, title: "Docker", responses: 200, created: "2024-11-05" },
            // { id: 5, title: "Web Services", responses: 150, created: "2024-11-01" },
            // { id: 6, title: "Java Programming", responses: 200, created: "2024-11-05" },
            // { id: 7, title: "Software Architecture", responses: 150, created: "2024-11-01" },
            // { id: 8, title: "English", responses: 200, created: "2024-11-05" },
        ],
        activeUsers: 0,
        totalForms: 0,

    };
    },

    methods: {
        async getActiveUsers() {
            try {
                const response = await utilisateurServices.getActiveUsers();
                this.activeUsers = response;
                console.log(this.activeUsers);
                
            } catch (error) {
                console.error(error);
            }

        },

        async getAllForms() {
            try {
                const response = await formServices.getAllForms();
                console.log(response);


                const groupedForms = response.reduce((acc, form) => {
                    const courseName = form.cours[0]?.nomCours;
                    if (!courseName) {
                        return acc;
                    }
                    console.log(courseName);
                    if (!acc[courseName]) {
                        acc[courseName] = {
                            id: form.id,
                            nomCours: courseName,
                            idCours: form.cours[0].id,
                            count: 0
                        };
                    }
                    acc[courseName].count += 1;
                    return acc;
                }, {});
                console.log(groupedForms);


                this.surveys = Object.values(groupedForms);
                console.log(this.surveys);
                this.totalForms = response.length;

            } catch (error) {
                console.error(error);
            }
        },


    },
    created() {
        this.getActiveUsers();
        this.getAllForms();
    }
};
</script>

<style scoped>
</style>
    