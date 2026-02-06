<script setup>
import NavBar from './components/NavBar.vue'
</script>

<template>
    <header>
        <NavBar />
    </header>
    <body>
      <Suspense>
        <template #default>
          <router-view />
        </template>
        <template #fallback>
          <h1>Cargando...</h1>
        </template>
      </Suspense>
    </body>
</template>

<style scoped>
</style>
