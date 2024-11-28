<script setup>
import { useI18n } from 'vue-i18n'

const { t } = useI18n()


</script>

<template>
    <div class="flex flex-col items-center space-y-4">
        <div class="flex-row space-x-8" >
            <input type="file" @change="handleFileUpload" class="mb-4 p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500" />
            <button 
                @click="startAnalysis" 
                :disabled="isProcessing"
                class="bg-indigo-500 text-white px-4 py-2 rounded-md shadow-md hover:bg-indigo-600 disabled:opacity-50"
            >
                {{ isProcessing ? t("Processing") : t("Start Update") }}
            </button>
            <div class="relative m-6 inline-flex w-fit">

</div>
        </div>

        <div class="flex-row">

            <div v-if="status.step === 3" class="bg-teal-100 border-t-4 border-green-500 rounded-b text-teal-900 px-4 py-3 shadow-md absolute top-0 right-0" role="alert">
                <div class="flex">
                    <div class="py-1"><svg class="fill-current h-6 w-6 text-green-500 mr-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M2.93 17.07A10 10 0 1 1 17.07 2.93 10 10 0 0 1 2.93 17.07zm12.73-1.41A8 8 0 1 0 4.34 4.34a8 8 0 0 0 11.32 11.32zM9 11V9h2v6H9v-4zm0-6h2v2H9V5z"/></svg></div>
                    <div>
                        <p class="font-bold">{{('Update Completed!')}}</p>
                        <p class="text-sm">New Teacher List added to the database</p>
                    </div>
                </div>
            </div>
            <!-- <p v-if="status.step === 3" class="completed-message text-green-500 font-semibold">{{('Update Completed!')}}</p>
            <p v-else class="text-gray-700">{{ status.message }}</p>
            <p v-if="status.percentage" class="text-gray-700">Progress: {{ status.percentage }}%</p> -->
        </div>
    </div>
</template>

<script>
import axios from "axios";


export default {
data() {
    return {
    file: null,
    status: { step: 0, message: "", percentage: 0 },
    isProcessing: false,
    };
},
methods: {
    handleFileUpload(event) {
    this.file = event.target.files[0];
    },
    async startAnalysis() {
    if (!this.file) {
        alert("Please select a file first.");
        return;
    }

    const formData = new FormData();
    formData.append("file", this.file);

    try {
        this.isProcessing = true; 
        const response = await axios.post("http://127.0.0.1:8000/analyze-excel-teacher",
        formData,
        {
            headers: {
            "Content-Type": "multipart/form-data",
            },
        }
        );
        console.log(response.data);
        this.startStatusUpdate();
    } catch (error) {
        console.error(error);
        this.isProcessing = false;
    }
    },
    async checkStatus() {
    try {
        const response = await axios.get("http://127.0.0.1:8000/status-teacher");
        this.status = response.data.status;

        if (this.status.step === 3) {
        this.isProcessing = false;
        this.stopStatusUpdate();
        }
    } catch (error) {
        console.error(error);
        this.stopStatusUpdate();
    }
    },
    startStatusUpdate() {
    this.statusInterval = setInterval(this.checkStatus, 500); 
    },
    stopStatusUpdate() {
    clearInterval(this.statusInterval);
    },
},
beforeDestroy() {
    this.stopStatusUpdate();
},
};
</script>

<style>
.completed-message {
color: green;
font-weight: bold;
}
</style>
  