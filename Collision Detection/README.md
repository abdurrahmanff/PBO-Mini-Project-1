# Penjelasan untuk Collision Detection
Program ini terdiri dari beberapa class yang membentuk game sederhana dengan mendeteksi adanya *collision* pada objek-objeknya.
Berikut penjelasan masing-masing class:
## Class Sprite
Kelas ini adalah bentuk dasar dari setiap objek yang akan dijadikan parent class dari setiap class pembentuk game object. Sprite memiliki beberapa properti, seperti x dan y yang merupakan posisi sprite tersebut di board game, width dan height yang merupakan ukurannya, boolean visible menandakan visibilitasnya di layar, dan image yaitu perwujudan sprite tersebut atau bentuk yang terlihat di dalam board. Di dalam sprite terdapat beberapa getter dan setter, selain itu ada fungsi getBounds() yang memiliki nilai return berupa persegi panjang di mana itu adalah bentuk dari pendeteksi collision.
## Class SpaceShip
Karena SpaceShip adalah sebuah game object, maka dia adalah child dari Class Sprite jadi dia mendapatkan semua fungsi dan properti dari parentnya. Namun, di dalam class ini ada properti yang ditambahkan yaitu dx dan dy yang merupakan nilai perpindahan ketika objek ini bergerak, dan juga missile yang menyimpan objek missile yg akan ditembakkan.
