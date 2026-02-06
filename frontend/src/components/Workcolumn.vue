<script setup>
import { computed } from 'vue';
import draggable from 'vuedraggable';
import WorkItemCard from './WorkitemCard.vue';

// Props: Recibimos el Título, el ID de columna y la lista de Tareas
const props = defineProps({
  id: { type: Number, required: true },
  title: { type: String, required: true },
  workitems: { type: Array, required: true }
});

// Emits: Para avisar al padre que la lista ha cambiado
const emit = defineEmits(['update:workitems', 'item-moved']);

// Computed Writable: Puente entre v-model y los props
const taskList = computed({
  get: () => props.workitems,
  set: (value) => {
    // Cuando draggable reordena, emite el array nuevo al padre
    emit('update:workitems', value);
  }
});

/**
 * Maneja el evento cuando soltamos una tarjeta.
 * Aquí es donde llamarás a tu API de Spring Boot.
 */
const onDragChange = (event) => {
  // 'added': Se soltó una tarjeta nueva en esta columna
  // 'moved': Se reordenó dentro de la misma columna
  // 'removed': Se fue a otra columna

  if (event.added) {
    console.log(`Tarjeta ID ${event.added.element.id} movida a columna ${props.title}`);

    // Emitimos un evento personalizado para que el padre llame a la API
    emit('item-moved', {
      taskId: event.added.element.id,
      newColumnId: props.id,
      newIndex: event.added.newIndex
    });
  }

  if (event.moved) {
    console.log(`Reordenado en ${props.title}. Nuevo índice: ${event.moved.newIndex}`);
    // Lógica para reordenar (cambiar sort_order en backend)
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
