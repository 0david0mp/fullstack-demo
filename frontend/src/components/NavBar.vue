<script setup>
  import { useRouter } from 'vue-router'

  const router = useRouter()
  // Obtenemos solo las rutas que tienen un nombre definido para el menú
  const menuItems = router.getRoutes()
</script>

<template>
  <nav class="navbar">
    <ul class="nav-links">
      <li v-for="route in menuItems" :key="route.path">
        <router-link :to="route.path" active-class="active">
          {{ route.name }}
        </router-link>
      </li>
    </ul>
  </nav>
</template>

<style scoped>
nav * {
  text-decoration: none;
  display: inline;
  padding: 2rem;
}
</style>

