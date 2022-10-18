<template>
  <div class="tRewardBox tcommonBox">
    <header>
      <h1>
        <a href="http://192.168.41.1:81/" target="_blank"> 写博文点这里 </a>
      </h1>
    </header>
    <section>
      <div>
        <img
          src="static/img/coffee.jpg"
          alt=""
          style="max-width: 20%"
          class="coffee"
        />
      </div>
      <h1>写博文说明：</h1>
      <p>
        博客采用Markdown语法格式，注意不要刷文章，以及注意文章不能含有低俗、敏感词汇，一起分享自己的学习笔记吧！
      </p>
      <hr />
      <h1>写博文方式：</h1>
      <p>
        点击标题“写博文点这里”打开嘻嘻博客管理系统。
      </p>


    </section>
  </div>
</template>

<script>
export default {
  name: "writer"
}
</script>

<style scoped>
.tRewardBox section {
  padding-bottom: 20px;
}
.tRewardBox section h1 {
  margin: 10px 0;
  font-size: 25px;
  font-weight: 700;
  /*text-align: center;*/
  line-height: 30px;
}
.tRewardBox section p {
  margin: 10px 0;
  line-height: 24px;
}
.tRewardBox section hr {
  background: #ccc;
  margin-bottom: 30px;
}

.tRewardBox .donate-item {
  text-align: right;
  color: #44b549;
}
.tRewardBox .donate-item:last-child {
  text-align: left;
  color: #00a0e9;
}
.tRewardBox .donate-item img {
  width: 100%;
  display: block;
  height: auto;
}
.tRewardBox .donate-item div {
  display: inline-block;
  width: 150px;
  padding: 0 6px;
  text-align: center;
  box-sizing: border-box;
}
.tRewardBox .donate-item div span {
  display: inline-block;
  width: 100%;
  margin: 10px 0;
  text-align: center;
}
.tRewardBox .el-table__body-wrapper {
  overflow: hidden;
}
.el-table--enable-row-hover .el-table__body tr:hover > td {
  background: transparent;
}
</style>
