<template>
  <div class="product">
    <div class="container py-3">
      <div class="d-flex justify-content-end pb-3"> 
        <button class="btn btn-outline-success mx-3" @click="()=>{addModal=true}">新增</button>
        <button class="btn btn-outline-danger mx-3" @click="deleteData()" >刪除</button>
      </div>
      <table class="table">
        <thead>
          <tr class="text-center">
            <th scope="col">#</th>
            <th scope="col">序號</th>
            <th scope="col">商品編號</th>
            <th scope="col">商品名稱</th>
            <th scope="col">商品價錢</th>
            <th scope="col">商品數量</th>
            <th scope="col">商品明細</th>
          </tr>
        </thead>
        <tbody>
          <tr class="text-center align-middle" v-for="(product, index) in currentProductArray" :key="index">
            <td><input class="form-check-input" type="checkbox" :value="product.proid" v-model="deleteProductDto.proid"></td>
            <th scope="row">{{ currentPage * 10 + index + 1 }}</th>
            <td>{{ product.proid }}</td>
            <td>{{ product.proname }}</td>
            <td>{{ product.proprice }}</td>
            <td>{{ product.proqty }}</td>
            <td><button class="btn btn-primary" @click="getDtData(product.proid)">明細</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  <div class="d-flex justify-content-center mb-3">
    <div class="btn-toolbar">
      <div class="btn-group me-2">
        <button type="button" class="btn btn-outline-primary" @click="changePage(currentPage - 1)">上一頁</button>
        <button type="button" class="btn btn-outline-primary" 
          v-for="(page,index) in Math.ceil((productList.length/10))" :key="page" 
          @click="changePage(index)">{{page}}</button>
        <button type="button" class="btn btn-outline-primary" @click="changePage(currentPage + 1)">下一頁</button>
      </div>
    </div>
  </div>


    <div v-if="dtModal"  class="su-modal" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">{{isModify ? "修改商品":"商品名細"}}</h4>
            <button type="button" class="btn-close" @click="() => {dtModal=false}"></button>
          </div>
          <div class="modal-body">

            <div class="d-flex mb-2">    
              <label for="proid" class="col-form-label">產品編號：</label>
              <input type="text" readonly="true" class="form-control-plaintext w-50" id="proid" v-model="productDt.proid">
            </div>
            <div class="d-flex mb-2">
              <label for="proname" class="col-form-label">產品名稱：</label>
              <input type="text" :readonly="!isModify" :class="modifyInputClass()" id="proname" v-model="productDt.proname">
            </div>
            <div>
              <div class="d-flex mb-2">
                <label for="proprice" class="col-form-label">產品價錢：</label>
                <input type="text" :readonly="!isModify" :class="modifyInputClass()" id="proprice" v-model="productDt.proprice">
              </div>
              <div class="d-flex mb-2">
                <label for="proqty" class="col-form-label">產品數量：</label>
                <input type="text" :readonly="!isModify" :class="modifyInputClass()" id="proqty" v-model="productDt.proqty">
              </div>
            </div>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="()=> {dtModal = false}">關閉</button>
            <button type="button" class="btn btn-primary" @click="modify()">{{isModify ? "確定":"修改"}}</button>
          </div>
        </div>
      </div>
    </div>

    <div v-if="addModal"  class="su-modal" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">新增商品</h4>
            <button type="button" class="btn-close" @click="()=> {addModal = false}"></button>
          </div>
          <div class="modal-body">

            <div class="d-flex mb-2">
              <label for="proname" class="col-form-label">產品名稱：</label>
              <input type="text" class="form-control w-50" id="proname" v-model="addProduct.proname">
            </div>
            <div>
              <div class="d-flex mb-2">
                <label for="proprice" class="col-form-label">產品價錢：</label>
                <input type="text" class="form-control w-50" id="proprice" v-model="addProduct.proprice">
              </div>
              <div class="d-flex mb-2">
                <label for="proqty" class="col-form-label">產品數量：</label>
                <input type="text" class="form-control w-50" id="proqty" v-model="addProduct.proqty">
              </div>
            </div>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="()=> {addModal = false}">關閉</button>
            <button type="button" class="btn btn-primary" @click="add()">新增</button>
          </div>
        </div>
      </div>
    </div>

    

  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { getProductList,getProduct,updateProduct,addProduct,deleteProduct } from "@/axios/axios"

interface Product {
  proid : number,
  proname : string,
  proprice : number,
  proqty : number,
}

export default defineComponent({
  name: "product",
  async mounted() {
    try {
      this.productList = (await getProductList()).data;
      this.currentProductArray = this.productList.slice(0,this.productList.length < 10 ? this.productList.length % 10 : 10);
    }catch(e){
      console.log(e);
    }
  },
  data() {
    return { productList: [] as Product[],
             currentProductArray: [] as Product[], 
             productDt: {} as Product, 
             addProduct: {
               proid :'',
               proname:'',
               proprice:'',
               proqty:'',
             },
             deleteProductDto:{
               proid : [],
             }, 
             isModify: false,
             addModal:false,
             dtModal:false,
             currentPage:0 as number };
  },
  methods : {
    modifyInputClass() {
      if(this.isModify){
         return 'form-control w-50';
      } else {
        return 'form-control-plaintext w-50';
      }
    },
    async getDtData(id:number) {
      try {
        this.dtModal = true;
        this.isModify = false;
        this.productDt = {} as Product;
        this.productDt = (await getProduct(id)).data;
      }catch(e){
        console.log(e);
      }     
    },
    async modify() {
      if(this.isModify) {
        try {
          await updateProduct(this.productDt);
          this.productList = (await getProductList()).data;
          alert("更新成功");
          this.dtModal = false;
          this.changePage(this.currentPage);
        }catch(e){
          console.log(e);
        }
      } else {
        this.isModify = !this.isModify;
      }
    },
    async add() {
      try {
        await addProduct(this.addProduct);
        this.productList = (await getProductList()).data;
        alert("新增成功");
        this.addModal = false;
        this.changePage(this.currentPage);
      }catch(e){
        console.log(e);
      }
    },
    async deleteData() {
      try {
          let products = this.deleteProductDto.proid.map(id => {
          let product = {proid:''};
          product.proid = id;
          return product;
        })
        deleteProduct(products);
        alert("刪除成功");
        this.productList = (await getProductList()).data;
        this.changePage(this.currentPage);
      }catch(e){
        console.log(e);
      }
    },
    changePage(page:number) {
      if(page < Math.ceil(this.productList.length / 10) && 0 <= page) {
        this.currentPage = page;
        let starPage = this.currentPage*10;
        let endPage = starPage + 10 > this.productList.length ? starPage + (this.productList.length % 10) : 10;
        this.currentProductArray = this.productList.slice(starPage,endPage);
      }
    }
  }
});
</script>

<style scoped lang="scss">
.su-modal {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 1055;
    width: 100%;
    height: 100%;
    overflow-x: hidden;
    overflow-y: auto;
    outline: 0;
}

.modal-body {
  font-size: 1.25em;
}

</style>