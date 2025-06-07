* encapsulation, inheritance, polymorphism, and abstraction  

* Concurrent HashMap > HashTable (bucket level lock)  

* TreeMap -> sorts based on keys  

* PUT is idempotent - POST is not  

* perform content negotiation (XML/JSON) in Rest endpoint (@GetMapping(produces = {"application/json", "application/XML"})) + add config in YAML  

* @ResponseStatus(HttpStatus.CREATED) => return 201 instead of 200 default  

* Upload file : functionName(MultipartFile file)  

* JPARepository implements PagingAndSortingRepository implements CrudRepository : crud + pagination, sorting, or JPA-specific features like flush() or saveAll()  

* for/while loop vs Stream : for/while is faster for smaller/medium data sets because no over head of lambda function creation - large datasets parallelStream will make difference  

```
hashCode() => returns the memory address hash
equals() => checks reference equality (i.e., obj1 == obj2)
both have to be overridden
```

