package com.example.homework.models

class PassengerCar(brand: String, model:String, year: Int, max_speed:Int,
                   var engine_type:String, var power:Double, var engine_capacity:Double) : PassengerCarInterface,Car(brand,model, year, max_speed){
        public override fun info(){
            println("Car info:")
            println(toString())
        }
        public override fun toString():String{
            return super.toString()+specifications()
        }

    public override fun specificationsInfo() {
        super.specificationsInfo()
        println("max speed: $max_speed km/h"+specifications())
    }
    private fun specifications(): String {
        var engine_unit =  ""
        if(engine_type =="Gas"||engine_type == "Diesel") engine_unit = "hp"
        else if (engine_type == "Electric") engine_unit = "kw"
        return "\n engine type: $engine_type \n power: $power" +
                " $engine_unit \n engine capacity: $engine_capacity"
    }

}