<template>
  <v-app>
    <!-- <v-app-bar
    app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />
      </div>

      <v-spacer></v-spacer>

      <v-btn
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
        text
      >
        <span class="mr-2">Latest Release</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar> -->

    <v-main>
      <v-card
        class="mx-auto"
        max-width="900">
        <todo-input v-on:addItem="addItem"></todo-input>
        <todo-list v-bind:items="items"/>
      </v-card>
    </v-main>
  </v-app>
</template>

<script lang="ts">
import TodoList from './components/TodoList.vue'
import TodoInput from './components/TodoInput.vue'
import { Component, Vue } from 'vue-property-decorator'
import { item } from './types/base-type'
import TodoApi from '@/lib/todo-api'

@Component({ components: { TodoList, TodoInput } })
export default class App extends Vue {
  private count = 0;
  public items: item[] = []

  mounted () {
    TodoApi.getTodoList().then(result => {
      this.items = result
    })
  }

  public addItem (title: string) {
    TodoApi.createTodo(title)
    // TODO : list 최신화가 되지 않음
    TodoApi.getTodoList().then(result => {
      this.items = result
    })
  }
}
</script>
