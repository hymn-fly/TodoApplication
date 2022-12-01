<template>
  <div>
    <v-text-field label="Add Todos" v-model="content" hide-details="auto" v-on:keyup.enter="addItem">
      <v-icon slot="append" color="red" v-on:click="addItem">mdi-plus</v-icon>
    </v-text-field>
  </div>
</template>

<script>
export default {
  name: 'TodoInput',

  data () {
    return {
      content: ''
    }
  },

  methods: {
    addItem () {
      if (this.content !== '') {
        console.log('addItem called')
        this.$emit('addItem', this.content)
      }
    },

    clearInput () {
      this.content = ''
    }
  }
}
</script>

<style>

</style>
