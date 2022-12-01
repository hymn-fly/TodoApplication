<template>
  <v-form ref="form" lazy-validation>
    <v-text-field
      v-model="email"
      :rules="emailRules"
      label="E-mail"
      required
    ></v-text-field>
    <v-text-field
      v-model="password"
      label="Password"
      required
    ></v-text-field>

    <v-btn
      color="success"
      class="mr-4"
      @click="login(email, password)"
    >
      로그인
    </v-btn>
  </v-form>
</template>

<script>
import { TodoApi } from '@/lib/todo-api'

export default {
  name: 'LoginComponent',
  data () {
    return {

      email: '',

      password: '',

      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid'
      ]
    }
  },

  methods: {
    login (email, password) {
      const userDto = {
        email,
        password
      }

      TodoApi.SignIn(userDto)
    }
  }

}
</script>

<style>

</style>
