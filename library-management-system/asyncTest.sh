for i in {1..100};
do 
echo $i;
curl --location --request GET 'localhost:8080/student/availableBooks' \
--header 'Authorization: Bearer e18cfed8-4373-4382-87f8-20835d23839d'
curl --location --request GET 'localhost:8080/student/availableBooks' \
--header 'Authorization: Bearer b0cd8478-bedb-4fb2-a5ad-3443028e33a6'
done
