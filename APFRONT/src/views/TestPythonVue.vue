<template>
    <div>
      <input type="file" @change="handleFileUpload" />
      <button @click="startAnalysis">Start Analysis</button>
      <p>{{ status.message }}</p>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        file: null,
        status: { step: 0, message: '' }
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
        formData.append('file', this.file);
  
        try {
          const response = await axios.post('http://127.0.0.1:8000/analyze-excel', formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          });
          console.log(response.data);
          // Iniciar la actualización automática del estado
          this.startStatusUpdate();
        } catch (error) {
          console.error(error);
        }
      },
      async checkStatus() {
        try {
          const response = await axios.get('http://127.0.0.1:8000/status');
          this.status = response.data.status;
        } catch (error) {
          console.error(error);
        }
      },
      startStatusUpdate() {
        this.statusInterval = setInterval(this.checkStatus, 2000); // Actualiza cada 2 segundos
      },
      stopStatusUpdate() {
        clearInterval(this.statusInterval);
      }
    },
    beforeDestroy() {
      this.stopStatusUpdate();
    }
  };
  </script>