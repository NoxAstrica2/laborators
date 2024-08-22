1. If you want to use testNG with dataprovider comment 
`testImplementation "org.junit.jupiter:junit-jupiter-api:$jupiter"
testRuntimeOnly "org.junit.jupiter:junit-jupiter-engine:$jupiter"
`
in dependencies section
<<<<<<< HEAD
=======

>>>>>>> c4d88aa (add lab0.md)
and 
comment 
`useJUnitPlatform()`
in test section

2. If you want to use Jupiter with Jupiter params comment
   `testImplementation group: 'org.testng', name: 'testng', version: '7.5.1'
   `
<<<<<<< HEAD
   in dependencies section 
=======
   in dependencies section

>>>>>>> c4d88aa (add lab0.md)
and
comment
`useTestNG()`
in test section