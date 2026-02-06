<template>
  <div class="kanban-card">
    <div class="card-header">
      <span class="id-badge">#{{ task.id }}</span>
      <button class="more-btn">...</button>
    </div>

    <div class="card-body">
      <h4>{{ task.title }}</h4>
      <p v-if="task.description" class="description">
        {{ truncate(task.description) }}
      </p>
    </div>

    <div class="card-footer">
      <span class="priority" :class="task.priority">{{ task.priority || 'Normal' }}</span>
    </div>
  </div>
</template>

<script setup>
// Definimos los props que recibe este componente
defineProps({
  task: {
    type: Object,
    required: true
  }
});

// Función auxiliar simple para no llenar la tarjeta de texto
const truncate = (text) => {
  return text.length > 50 ? text.substring(0, 50) + '...' : text;
};
</script>

<style scoped>
.kanban-card {
  background-color: white;
  border-radius: 8px;
  padding: 12px;
  margin-bottom: 10px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.05);
  border: 1px solid #e2e8f0;
  cursor: grab; /* Importante: indica que se puede agarrar */
  transition: transform 0.2s, box-shadow 0.2s;
}

.kanban-card:hover {
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}

.kanban-card:active {
  cursor: grabbing;
}

.card-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
  font-size: 0.8em;
  color: #64748b;
}

.id-badge {
  background: #f1f5f9;
  padding: 2px 6px;
  border-radius: 4px;
}

h4 {
  margin: 0 0 5px 0;
  font-size: 1em;
  color: #334155;
}

.description {
  font-size: 0.85em;
  color: #94a3b8;
  margin: 0;
}

.priority {
  font-size: 0.75em;
  font-weight: bold;
  text-transform: uppercase;
}
</style>
