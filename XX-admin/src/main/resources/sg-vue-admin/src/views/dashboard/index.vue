<template>
  <div class="dashboard-container">
    <div class="dashboard-text">
      嘻嘻博客管理系统
    </div>
    <div>
      普通用户只有写博文的权限，需要获取更多权限请联系超级管理员QQ：244665429
    </div>
    <div>
      其他权限还有用户管理、角色管理、菜单管理、文章管理、分类管理、标签管理、友链管理等等
    </div>
    <div>

    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
