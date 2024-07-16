package com.example.unittestapp

class MainViewModel(private val cuboidModel: CuboidModel) {
    fun getCircumreference() = cuboidModel.getCircumreference()
    fun getSurfaceArea() = cuboidModel.getSurfaceArea()
    fun getVolume() = cuboidModel.getVolume()
    fun save(w: Double, l: Double, h: Double) {
        cuboidModel.save(w, l, h)
    }


}