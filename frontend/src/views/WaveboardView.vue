<script setup>
import { ref } from 'vue';
import WorkColumn from '../components/Workcolumn.vue';

// Estructura simulada de lo que viene de tu Java/DB
let cols = await getColumns();
const columns = ref(cols);

console.log(columns.value)

const handleMoveAPI = ({ taskId, newColumnId, newIndex }) => {
   console.log(`Llamando a /api/move task:${taskId} newColumn:${newColumnId}, newIdx:${newIndex}`);
}

async function getColumns() {
  try {
    const response = await fetch('/api/workcolumn');

    if (!response.ok) {
      throw new Error(`ERROR: ${response.status}`);
    }

    const columns = await response.json();

    return columns;
  } catch (error) {
    console.error('Error:', error);
  }
}

</script>

<template>
    <div class="board">
      <WorkColumn
        v-for="col in columns"
        :key="col.id"
        :id="col.id"
        :title="col.name"
        :workitems="col.workitems"
        @update:items="newItems => col.workitems = newItems"
        @item-moved="handleMoveAPI"
      />
    </div>
</template>

<style>
.board { display: flex; gap: 20px; padding: 20px; overflow-x: auto; }
</style>
