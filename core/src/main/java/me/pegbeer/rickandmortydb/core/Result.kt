package me.pegbeer.rickandmortydb.core

data class Result<out T>(val status:Status, val data:T?, val code:Int?){

    enum class Status{
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object{

        fun <T> success(data:T):Result<T>{
            return Result(Status.SUCCESS,data,null)
        }

        fun <T> error(code:Int):Result<T>{
            return Result(Status.ERROR,null,code)
        }

        fun <T> loading():Result<T>{
            return Result(Status.LOADING,null,null)
        }
    }
}