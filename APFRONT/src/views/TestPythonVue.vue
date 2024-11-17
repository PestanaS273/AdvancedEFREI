<template>
    <div>
      <input type="file" @change="handleFileUpload" />
      <button 
        @click="startAnalysis" 
        :disabled="isProcessing"
      >
        {{ isProcessing ? "Processing..." : "Start Analysis" }}
      </button>
      <p v-if="status.step === 3" class="completed-message">Analysis Completed!</p>
      <p v-else>{{ status.message }}</p>
      <p v-if="status.percentage">Progress: {{ status.percentage }}%</p>
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
        const response = await axios.post(
        "http://127.0.0.1:8000/analyze-excel",
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
        const response = await axios.get("http://127.0.0.1:8000/status");
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
  