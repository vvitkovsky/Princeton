sort(S);
for i = 0 to n - 2 do
   a = S[i];
   start = i + 1;
   end = n - 1;
   while (start < end) do
       b = S[start]
       c = S[end];
       if (a + b + c == 0) then
           output a, b, c;
           // Continue search for all triplet combinations summing to zero.
           // We need to update both end and start together since the array values are distinct.
           start = start + 1;
           end = end - 1;
       else if (a + b + c > 0) then
           end = end - 1;
       else
           start = start + 1;
   end
end