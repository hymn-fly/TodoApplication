<template>
    <div>
        <v-text-field label="Add Todos" v-model="content" hide-details="auto" v-on:keyup.enter="addItem">
            <v-icon slot="append" color="red" v-on:click="addItem">mdi-plus</v-icon>
        </v-text-field>
    </div>
  </template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component
export default class TodoInput extends Vue {
    public content!: string;

    public addItem () {
      if (this.content !== '') {
        this.$emit('addItem', this.content)
        this.clearInput()
      }
    }

    public clearInput () {
      this.content = ''
    }

    data () {
      return {
        content: undefined
      }
    }
}
</script>

<style>

</style>
