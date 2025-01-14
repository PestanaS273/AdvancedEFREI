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

        <h1 class="text-xl font-bold text-gray-800 my-6">{{ t('Forms To Complete') }}</h1>
        <DataTableToComplete :data="surveys" />
        <br>
        <h1 class="text-xl font-bold text-gray-800 my-6">{{ t('Completed Forms') }}</h1>
        <DataTable :data="surveys" />
    </div>

</template>

<script>
import StatsCard from "../../components/Dashboard/StatsCard.vue";
import DataTable from "../../components/Dashboard/DataTable.vue";
import DataTableToComplete from "../../components/Dashboard/DataTableToComplete.vue";
import utilisateurServices from '../../services/utilisateur.services';
import formServices from '../../services/form.services';




export default {
components: { StatsCard, DataTable, DataTableToComplete },
data() {
    return {
        surveys: [

        ],
        dataToComplete: [
        //   { id: 1, title: "Enquête sur le crous", responses: 0, created: "2024-11-01" },
        //   { id: 2, title: "Opinion sur les locaux", responses: 0, created: "2024-11-05" },
      ],
      activeUsers: 0,
      totalForms: 0,
    };
    },

    methods:{
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
                    const userConnected = this.$store.state.user.user.email;
                    console.log(userConnected);
                    if (form.etudiant.email === userConnected) {
                        if (!acc[courseName]) {
                            acc[courseName] = {
                                id: form.id,
                                nomCours: courseName,
                                idCours: form.cours[0].id,
                                count: 0
                            };
                        }
                        acc[courseName].count += 1;

                    }



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

    async created() {
        await this.getActiveUsers();
        await this.getAllForms();
    },  
};
</script>

<style scoped>
</style>
    