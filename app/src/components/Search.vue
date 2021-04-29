<template>
  <div>
    <div>
        <h3>Game Rental Search</h3>
        <form @submit="doSearch">

        <p>
            <input
              id="query"
              v-model="query"
              type="text"
              name="query"
              placeholder="Search query"
            >
        </p>

        <p>
          <input
            type="submit"
            value="Submit"
          >
        </p>
        </form>
    </div>
    <div class="SearchResults">
      <div v-for="game in searchResults" v-bind:key="game.name">
         {{game.name}}
       </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import axios from 'axios'

export interface Game {
  name: string;
  thumbnail: string;
}

export default defineComponent({
  name: 'SearchResults',
  data () {
    return {
      searchResults: [],
      query: ''
    }
  },
  methods: {
    doSearch: async function () {
      console.log('Searching for ' + this.query)
      axios
        .get('http://localhost:8090/search/' + this.query)
        .then(response => { this.searchResults = response.data })
    }
  }

})
</script>

<style scoped lang="scss">
  search {
    padding: 20px;
    padding-left: 55px;
    height: 20px;
    width: 300px;
    border: 1px solid #f5f5f5;
    font-size: 13px;
    color: gray;
    background-repeat: no-repeat;
    background-position: left center;
    outline: 0;
  }
</style>
