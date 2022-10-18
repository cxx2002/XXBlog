<template>
<div><sg-nav></sg-nav>
  <div class="container">
    <el-row :gutter="30">
      <el-col :sm="24" :md="16" style="transition:all .5s ease-out;margin-bottom:30px;">
        <sg-writer></sg-writer>
      </el-col>
      <el-col :sm="24" :md="8">
        <sg-rightlist></sg-rightlist>
      </el-col>
    </el-row>
  </div></div>
</template>

<script>
import header from '../components/header.vue'
import rightlist from '../components/rightlist.vue'
import reward from "../components/writer";
import writer from "../components/writer";
export default {
  name: "WriterArticle",
  data() { //选项 / 数据
    return {

    }
  },
  methods: { //事件处理器

  },
  components: { //定义组件
    'sg-nav': header,
    'sg-writer': writer,
    'sg-rightlist': rightlist,
  },
  created() { //生命周期函数

  }
}
</script>

<style>

</style>
