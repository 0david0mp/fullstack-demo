<script setup>
import { computed } from 'vue';
import draggable from 'vuedraggable';
import WorkItemCard from './WorkitemCard.vue';

// Props: Recibimos el Título, el ID de columna y la lista de Tareas (desde ../views/WaveboardView.vue)
// SOLO LECTURA, para modificarlos: emit() -> onDragChange
const props = defineProps({
  id: { type: Number, required: true },
  title: { type: String, required: true },
  workitems: { type: Array, required: true }
});

const emit = defineEmits(['update:items', 'item-moved']);

// para no utilizar directamente los props
const taskList = computed({
  get: () => props.workitems,
  set: (value) => {
    emit('update:items', value);
    // se lo manda a ../views/WaveboardView.vue:41
  }
});

const onDragChange = (event) => {
  if (event.added) {
    emit('item-moved', {
      taskId: event.added.element.id,
      newColumnId: props.id,
      newIndex: event.added.newIndex
    });
  }

  if (event.moved) {
    console.log(`Llamando a /api/order col:${props.id} newIndex: ${event.moved.newIndex}`);
  }
};
</script>

<template>
  <div class="column-container">
    <div class="column-header">
      <h3>{{ title }}</h3>
      <span class="count-badge">{{ workitems.length }}</span>
    </div>

    <draggable
      v-model="taskList"
      group="kanban-group"
      item-key="id"
      class="drag-area"
      ghost-class="ghost-card"
      @change="onDragChange"
    >
      <template #item="{ element }">
        <WorkItemCard :task="element" />
      </template>
    </draggable>
  </div>
</template>

<style scoped>
.column-container {
  background-color: #f8fafc;
  width: 300px; /* Ancho fijo o min-width */
  min-height: 500px;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  border: 1px solid #cbd5e1;
}

.column-header {
  padding: 16px;
  border-bottom: 2px solid #e2e8f0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  border-radius: 12px 12px 0 0;
}

h3 {
  margin: 0;
  font-size: 1.1em;
  color: #1e293b;
}

.count-badge {
  background-color: #e2e8f0;
  border-radius: 12px;
  padding: 2px 10px;
  font-size: 0.8em;
  font-weight: bold;
}

.drag-area {
  flex-grow: 1; /* Ocupa todo el alto restante */
  padding: 10px;
  min-height: 100px; /* Importante para poder soltar si está vacía */
}

/* Estilo para el hueco donde va a caer la tarjeta (Ghost) */
.ghost-card {
  opacity: 0.5;
  background: #cbd5e1;
  border: 2px dashed #94a3b8;
}
</style>
