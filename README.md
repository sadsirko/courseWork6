In this paper, the design of a time-efficient and processorefficient parallel algorithm for the integral knapsack problem is
considered. A parallel integral knapsack algorithm is presented,
which is adaptive to all parameters, especially to the maximum
size of items. The parallel complexity of another important
packing problem, the integral exactly-packing problem, is also
considered. An optimal O(log n log m) time, parallel integral
exactly-packing algorithm is given. Since the partition problem
has a constant time, constant processor reduction to the exactlypacking problem, our parallel integral exactly-packing algorithm can be used for job scheduling, task partition, and many
other important practical problems. Moreover, the methods and
techniques used in this paper can be used for developing processor-efficient and time-efficient parallel algorithms for many
other problems. Using the new parallel integral knapsack algorithm, the previously known parallel approximation schemes for
the O-l knapsack problem and the binpacking problem, by
E. W. Mayr and P. S. Gopalakrishnan, are improved upon
significantly. 0 1990 Academic Press, Inc.
1. INTRODUCTION
The problem considered in this paper is the integral knapsack problem. The integral knapsack problem, denoted by
9Yw(9,, . . .) 9,, m), can be stated as: given n different
kindsofitemsJ,,..., .7, and a knapsack of size m E JV,
where the ith kind of item Jj has size Si E N and value Y;,
find a combination (cl, . . . , cn) E JV” such that CFI CiSi
G m and the total value C y=, ciYi, is maximized.’
The integral knapsack problem has many applications.
An important one is in developing efficient sequential or
parallel approximation algorithms for various NP-complete
problems, e.g., the 0- 1 knapsack problem and the binpacking problem. By incorporating the optimal parallel integral
* Supported in part by the National Science Foundation through Grant
DCR-85 1496 I. Present address: School of Computer Science, Carnegie
Mellon University, Pittsburgh, PA I52 13.
’ We can easily extend our result and algorithm for the integral knapsack
problem when the number of copies in each kind of items is finite instead
of infinite.
knapsack algorithm with the parallel approximation O-l
knapsack algorithm of Mayr [ 131, the following result is
achieved.
THEOREM 1.1. Given t > 0 and an instance of the O-l
knapsack problem, a solution with a pro$t at least ( 1 - E)
times optimal can befound in time O(log n log(n/t)) on a
CREW PRAM with n 2 / t processors.
This result improves upon the reviously known results
in [ 13,6] by a factor of n/e and F n/e, respectively.
The general knapsack problem, in which the capacity of
the knapsack and the sizes and values of items might not be
integral, was proven to be NP-hard by Karp [ 91. However,
it is known that the integral knapsack problem can be
solved in 0( mn) time sequentially via a dynamic programming technique [ 201.
Knowing that any circuit of polynomial degree over a
commutative semiring can be evaluated in polylogarithmic
time, using a polynomial number of processors [ 15, 161,
one can easily parallelize the sequential algorithm in [ 201 to
construct an 0( log2mn) time, M( mn) processor2 parallel
algorithm for the integral knapsack problem. We shall show
that a more processor-efficient parallel algorithm can be developed.
In particular, it is proven in Section 3 that, with
0( log mn) time, mn/log mn processor preprocessing, the
integral knapsack problem can be reduced to a circuit value
problem of m nodes and constant degree over a commutative semiring { JV, max, + } . Incorporating the parallel circuit evaluation algorithm of Miller et al. [ 15, 161, we present an 0( log2mn) time, M(m) processor parallel integral
knapsack algorithm.
In many practical applications, s = max y= 1 Si is much less
than m, the size of the knapsack, i.e., s -+ m . It is shown in
Section 4 that, with O(log mn) time, mn/log mn processor
preprocessing, the integral knapsack problem can be reduced to the prejx products problem of t = L ( m + 1 )/sJ
matrices with size s. This yields an O((log m/s)(log2s)) 
time, M(s) m / ( s log m/s ) processor parallel integral knapsack algorithm.
The parallel complexity of another important packing
problem, the integral exactly-packing problem, is also considered. The integral exactly-packing problem, denoted by
fBJN(J,, . . . , J,, m), can be described as: given n different kinds of items 3,) . . . , J,, of sizes S, , . . . , S,, respectively, find a combination (cl, . . . , c,,) E N” such that
C := 1 ciSi = m , if one exists.
An optimal 0( log n log m) time parallel integral exactlypacking algorithm is given. This involves an on-line reduction from the integral exactly-packing problem to the prefix
convolution problem of n integral-vectors with size m. A
similar method is applied to develop an O(log m log n)
time, m2n/log m log n processor parallel algorithm for the
integral knapsack problem. Note that this parallel algorithm is more efficient when n + min ( m , s) .
It is known that thepartitionproblem has a constant time,
constant processor reduction to the exactly-packing problem [ 7 1. As a consequence, our algorithm can be used for
job scheduling, task partition, and many other problems [ 7,
21, 81.
The model of parallel computation used in this paper is
the parallel random access machines (PRAM) [ 4, 221. All
our algorithms can be implemented randomly on many realistic message-passing-based parallel machines, such as
hypercube-based connection machines and butterfly networks, with at most a factor of (log n) increasing of the run
time [24, 181.
2. SOME GENERAL TECHNIQUES
IN PARALLEL COMPUTATION
2. I. Parallel Tree Contraction
Tree contraction is a bottom-up procedure to reduce a
tree to its root. Miller and Reif [ 141 gave an 0( log n) time,
O(n) processor deterministic parallel algorithm for tree
contraction on a CRCW PRAM. Gazit et al. [ 51 optimized
the parallel tree contraction algorithm via a new tree reduction technique and implemented the algorithm on an
EREW PRAM in 0( n/ P) time, using P processors deterministically, where P < n/log n .
THEOREM 2.1 (Tree contraction [ 51). Tree contraction
can be performed in O(n/P) time using P processors (P
< n/log n) on an EREWPRAM.
The parallel tree contraction technique can be used to
develop optimal parallel algorithms for many tree-based
graph problems and algebraic problems [ 14, 17 1. We now
show how to use the parallel tree contraction technique to
solve the ancestor finding problem, which will be used later
in the paper.
DEFINITION 2.1 (Ancestor Finding Problem). Let Frst
be a forest with n vertices and let one of its leaves Iv be
marked; mark all the vertices which are ancestors of Iv.
LEMMA 2.1 (Ancestor-Finder). The ancestor finding
problem3 can besolved in O(logn) time, O( n/logn)processors on an ERE W PRAM.
Proof Let us assign the marked leaf value 1 and all
other leaves 0; let all the internal vertices be “+” operations.
Using the parallel tree contraction technique [ 17 1, the values of all vertices can be computed in O(log n) time using
0( n/log n ) processors. Clearly, an internal vertex has value
1 iff it is an ancestor of the marked leaf. This procedure is
called the ancestor-finder. n
2.2. Parallel Evaluation of Circuits
Let34.={YJ,@, ,..., Ok,@, ,..., Q}beanalgebraic
system, 3 be a set of unary functions from ZZJ to ZOO. A computation circuit @ is a labeled directed acyclic simple graph
whose leaf nodes, nodes with in-degree zero, are labeled
with values from a>; whose internal nodes are labeled with
operators from the set of operators; and whose edges are
labeled with unary functions from 3. The value of a node n
in @, denoted by value(v), is defined inductively as:
l If u is a leaf, then value(u) is the value associated
with u.
l If u is an internal node labeled by 0, then value(v)
= o(f,(value(v,)), . . . , fk( value( Q)), where vI, . . . , uk
are the children of u. f, , . . . , fk are the unary functions with
edges (v, v), * f * 3 (uk, u>.
The circuit valueproblem over { 3, @ } is defined as computing the values of all nodes in @.
In 1975, Ladner [ lo] showed that the circuit value problems over any Boolean algebra { { 0, 1 } , V, A } is log space
complete for P. In spite of the p-complete result of the general circuit value problem, recent studies of Valiant et al.
[ 25 1, Miller et al. [ 15 1, and Miller and Teng [ 16 ] showed
that a large class of circuits can be evaluated in polylogarithmic time, using a polynomial number of processors. The
following is the main result in [ 15 1.
THEOREM 2.2 [ 15 1. Zf @ is a circuit over a commutative
semiring { 3, 0, 0 } of n nodes and degree“ d, then e can be
evaluated in 0( (log n)(log nd)) time, using at most M(n)
processors.
3. A SIMPLE ALGORITHM
An integral knapsack problem, J.J@‘( 3,) . . . , J,,
m ) , has four important parameters, m, n , max$ , Sj,  max$=, Vi. Unlike the sequential integral knapsack algorithm, the parallel integral knapsack algorithm seems to depend on all those parameters. In this paper, three parallel
integral knapsack algorithms are given. Besides the
O( log mn) time, mn/log mn processor preprocessing cost,
the first algorithm takes O( log2m) time, using M(m) processors; the second uses O((log m/s)log2s) time and
M( s)m/( s log m/s) processors; and the third takes
O( log m log n) time, using m2n/log m log n processors.
Following from a simple analysis, it can be shown that the
third algorithm is the most efficient one when n + min(s,
m); the second is the most efficient one when s -G m; while
the first is the most efficient one in the general case.
The method used for constructing an efficient sequential
integral knapsack algorithm is dynamic programming, i.e.,
building an m by n tables whose ( i, j) th entry contains the
maximum total value for the integral knapsack problem
JXP(3,). . . , .7i,j) and the corresponding optimal combination. Since the (i, j)th entry in the table can be determined solely by the (i, j - Si)th entry, the value of each
entry as well as the optimal combination of each entry can
be computed in one pass.
In order to obtain maximum parallel speedup, different
techniques are used. It is observed that it is difficult to maintain the clarity of the algorithm and the simplicity of the
analysis if we try to compute maximum values of subproblems as well as optimal combinations in one pass. Therefore, the following two-pass paradigm is proposed.
l IKP Computing Step. Compute the important information needed for the construction of an optimal combination of JJfF’( 3,). . . , J,, m).
l IKP Constructing Step. Use the information provided in the IKE Computing Step to construct an optimal
combination for JHP( 3,) . . . , J,, m).
3.1. A Reduction to the Circuit Value Problem
LEMMA 3.1. zf(C,, . . . , c,) is an optimal combination
for JJ@(J,, . . . , J,,m),andci>O, then(c,,. ..,ci- 1,
c,,)
hi” Si).
is an optimal combination for J%!p( J1, . . . , J,,
Proof If there exists an optimal combination (c’, , . . . ,
cl,) for JFS(J1, . . . , J,, m - Si) such that Cj”= 1 c;Vj
> ( C:=, CjVj) - Vi, then it follows that (c; , . . . , C: + 1,
. . . ) CL) is a feasible combination for JXI>( 3,) . . . , J,,
m); and
iCjV,+Yi>(iCjVj)*
j=l j=l
This contradicts the assumption in the lemma. n
Let value,[ j] denote the maximum value of JX‘F’( 3,)
. . . ) J,, j). It follows from Lemma 3.1 that for all j (0 G j
<m )
value, [ j]
i
0, j=O
= max(value,[j- 11, (1)
m”ax value,,[ j - Sk] + Yk) otherwise.
k=l,jdk
Note that the total value of an optimal combination for
JJf‘Z’(J1,. . . , I,, m) is equal to value,, [ m] . We shall show
how to compute value,, [ 01, . . . , value,, [ m] in parallel.
Let 6’ = { Y, G } be a max-plus circuit where
l v = {Ilo, 01, u2,. .
. , urn}, and &’ = {(uj-ai7 Vj)l 1 < i
=Gn,SiGjGm}.
l Vj is labeled by “max” and v. is labeled by 0.
l Edge (Uj-ai, Vj) is labeled by unary function x + Vi.
It can be proven easily by induction that for all j, the
value of Uj is equal to value,[ j] . Hence, the evaluation of @
gives value,[O], . . . , value, [m] . Moreover, because e has
mn edges, e can be constructed in O( log mn) time, using
mn/log mn processors.
The degree of @ is one because 6’ contains only max
nodes. Therefore, using the parallel circuit evaluation algorithm from [ 15, 161, @ can be evaluated in O( log2m) time,
using M(m) processors. Consequently,
LEMMA 3.2. With 0( log mn) time, mnllog mn processor preprocessing, value,,[ 01, . . . , value,, [ m] can be computed in O( log2m) time, usingM( m) processors.
3.2. Constructing Optimal Combinations in Parallel
It will be shown that given value,,[ 01, . . . , value,,[ m] ,
an optimal combination of JX?‘( 3,) . . . , J,, m) can be
computed in 0( log mn ) time, using mn / log mn processors.
Consequently,
THEOREM 3.1. An optimal combination of J.J@( J,,
. . , J,, m) can be computed in O(log m log mn) time, using max( mn / log mn, M(m)) processors.
Let La)92 be a labeled relation
+ vk} u {(i- 1, i, e)Ivalue,[i] = value,[i- I]}.
Clearly, (i, j, k) E L a)32 means that an optimal combination for JX%?( 3,) . . . , 3,) j) can be obtained by adding
one more Jk to an optimal combination for JXP (3,) . . . ,
J,, i); while (i - 1, i, e) E LCD.99 means that all optimal
combinations for JFS( 3,) . . . , J,, i - 1) are optimal
combinations for JX’P ( J1, . . . , 3,) i).
On the basis of relation L DR, the labeled directed graph
La)O = {Y, & } is constructed, where ‘-V = { uo, ul, . . . ,
V, } and & is constructed as: for all j, choose one i such that (i, j, k) E -C DB (if there is more than one such i, choose
one of them arbitrarily); then make (j, i) an edge in f a)&’
with label k .
Since the relation La)% has no more than mn elements,
L a09 can be constructed in 0( log mn) time, using mn/
log mn processors.
Note that each node in L 2XJ has only one parent, and it
is easy to prove by induction that all nodes in LB39 are
connected with vo. Therefore LX&’ is a tree rooted at vo.
Note also that v, is a leaf in L a09 because the fan-in of v,
is zero. Thus,
LEMMA 3.3. Let WIW2.. . . , w,, where w, = V,, W, = v,,
be the path in IDS from v, to vo; let ki denote the label of
edge(wi,wi+l);andletcj= I{ki:ki=j}l;then(c,,...,
c,) is an optimal combination for JHP( J,, . . . , J,, m).
Let w,w2,. . . , w,, where wI = v,, w, = vo, be the path in
&DBfromv,tovo.Itisobservedthat{wi) 2&i<s)is
the set of all ancestors of 21, in L DB. Hence, the path from
v,,, to v. can be constructed in 0( log m) time, using 0( m/
log m) by ancestor-finder (see Lemma 2.1, Section 2). Consequently, the IKP constructing step is reduced to the following occurrence$ndingproblem in 0( log mn) time, using
0( mn/log mn) processors.
DEFINITION 3.1 (Occurrence Finding Problem). Given
Z={ul,..., a,} and W= wI . . . w, E Z”, find the occurrence of each letter ci in W.
It can be shown easily that the occurrence finding problem can be solved in O(log mn) time, using 0( mn/log mn)
processors. This procedure is called occurrence-Jinder.
We conclude this section with the following parallel integral knapsack algorithm.
Algorithm Integral Knapsack(SB?‘( J,, . . . , J,, m))
(1) compute value,[ I], . . . , value,[n];
(2) construct the labeled relation L 0%;
( 3) construct the labeled directed graph f 2X’;
(4) use ancestor-finder to mark the path in -C DB from
V,~OVo,
letW=k, . . . k, be the string of labels on the path
from v, to v,;
(5) calloccurrence-finder({l,...,n,c}, W);
(6) output(cl,. . . ,c,);
4. IMPROVE THE ADAPTIVITY
OF THE ALGORITHM
In this section 8, ( 1 < i < n), the size of each item Ji,
is taken into consideration. It will be shown that if s
= max:, , Si, then with 0( log mn) time, mn/log mn processor preprocessing, { value,, [ j] I I< j c m } can be computed
inO((logm)(log2s))time,usingonlyM(s)m/(slogm/s)
processors. Hence, the resulting parallel algorithm is more
efficient when s 4 m .
DEFINITION 4.1. v E N U { -co } linearly depends on
Vl,. * * 7 V,EJVU {-co},ifthereexista,,. . . ,a,EJJsuch
that v = max~=~(ai + Vi).
From relation ( 1) , it follows that for all j > s, value, [ j]
depends linearly on value,, [ j - s] , . . . , value,, [ j - l] because if one lets ai = max,+-o,=i( Ilk, -00 ), then valuen[ j]
= max$ t (value,, [ j - i] + ai). Therefore, it can be proven
by induction that for all j 3 s, value,[ j], . . . , value,,[ j + s
- I] all depend linearly on value,, [ j - I], . . . , value, [ j
- s] . For simplicity, let Vi denote value,[ i] ; then for all j,
there exists a matrix A S’s of size s such that
/ vj+s-l \ /vi-l\
where the operation is defined on semiring { JV U { -co } ,
max, + > , i.e.,
Without loss of generality, it is assumed that m + 1 is
divisible by s and t = (m + 1 )/s, and it follows from the
above discussion that there are t - 1 matrices A 1, . . . , A,-,
of size s such that
\ / vis \v(i-l)s / \ vo /
(2)
LEMMA 4.1. (v,. . . . , v,-I ) can be computed in
O(log2s) time, using M(s) processors; and for all i, Ai in
Eq. (2) can be constructed in O(log2s) time, using M(s)
processors.
Proof: The first part of the lemma follows from the
proof in Lemma 3.2. We now prove the second part of the
lemma. For each i, let Bi be a 2s by 2s matrix over N
U{-colas
(Bi1j.k
0, s+l<j=k<2s
= max (v,), 3t(l<trn)suchthatk-S,=j
t:k-S,=j
-co otherwise.
The following is a pictorial pattern of the matrix Bi. Note
that -00 and 0 are the zero and unit of semiring { N
U{-oo},max,+}. SHANG-HUA TENG
---co *
-co -03
-00 -co
--co -co
t :
--Go --co
-co --co
-co -00
-us -co
* * * *
* * * *
-00 * * *
. . . .
. . . .
. . . .
. . . -00
. . . -0c)
. . . -a
. . . -co
. . . .
. . . .
. . . .
. . . -a
. . . *
. . . *
. . . *
*
-03 ii
-co -co
-co -co
-co -co
Claim 4.1. For all i ( 1 < i < t - 1 ), the constructed B,
has the following properties:
(2) LetC,,C3,CqbesbysmatricesoverNU(-cc}),
suchthatforallj,k(l <j,k<s),
(C,),, = K’3)j,k = --co and
I 0
(Cd)j,k =
if j=k
-al otherwise;
then
where (Bi)’ denotes the 8th power of Bi under the matrix
multiplication defined on semiring { N U { - oc, } , max, + } ;
and Ai is the matrix in relation (2 ) .
Proof Statement (1) follows the definition of Bi
straightforwardly. Statement (2) can be proven by induction on s. Actually, the proof is quite similar to the proof of
computing the transitive closure of a graph or the proof of
computing the shortest paths between all pairs of nodes in
a weighted graph by matrix multiplication [ 121.
Since each Bi can be constructed in 0( log s) time, using
O(s2) processors, by computing the s power of Bi in
0( log2s) time, using M(s) processors, Ai in Eq. (2) is acquired free. n
The following is a parallel algorithm for the IKP Computing Step based upon the above discussion.
Procedure IKP-Computing-Step( J.J@( J,, . . . , J,, m))
(1) inparailelforalli(l~i<t- 1)do
construct 2s by 2s matrices Bi;
compute (Bi)’ and get Ai;
( 2 ) compute the values of u. , . . . , u,-, ;
( 3) use the parallel prefix products algorithm to compute
the values of all Vi.
-co
*
*
*
-co
0
-al
-03
. . . -co
-co . . .
* . . .
* . . .
. . .
-a3 . . .
0 . . .
-co . . . 0
THEOREM 4.1. With O(log mn) time, mnllog mn processor preprocessing, an optimal combination of JXP (9, ,
. . , J,, m) can becomputedin O((log m/s)(log2s)) time,
usingM( s)/( s log m/s), processors.
5. ALGORITHM FOR INTEGRAL
EXACTLY-PACKING PROBLEM
By assigning ?li = Si, the integral exactly-packing problem can be reduced to the integral knapsack problem.
Hence, the parallel integral knapsack algorithm can be used
for solving the integral exactly-packing problem. In this section, a more efficient parallel integral exactly-packing algorithm is presented.
Foralli,j,k,l<i<j<n,l<k<m,let
f 1 if 3(Ci, . . . 2 Cj) E N’-‘+’
bin[i: j; k] =
I such that i cJI = k
I I=i
0 otherwise.
Clearly,foralli,k, 1 <i<n, 1 <k<m,
bin[i:i;k]= I 1, k is divisible by Si
0 otherwise.
Note also that 8JN( J, , . . . , J,, m) has a solution iff
bin [ 1 : n ; m] = 1. The following lemma can be established
easily.
LEMMA 5.1 (Combination Lemma). For all i, , i2, i3 ( 1
. < iI < l2 < i3 G n), let
(a,,. . . , a,)=(bin[i,:i2;l],...,bin[i,:i2;m])
(h,. . .,b,)=(bin[i2+l:i3;1],...,bin[i2+1:i3;m]).
Let 0 be the convolution operation over m-integral-vectors, i.e., forallA = (4,. . . , a,-,), B = (b,, . . . , bMml):A
OB=(co,... , c,,-~) is defined as ci = Cl+t=r albl. Let Z(x) be a function from the set of natural numbers LEMMA 5.2. For all A, B E 2 n, A 0 B can be computed
NU{O}to{O,l)suchthat in 0( log n) time, using O(n) processors.
l
1 if x+0 Proof Since each recursive call reduces the size of the
Z(x) = vector by half, the depth of the recursive call is bounded by
0 x=0. 0( log n). It is easy to verify that step (2) takes constant
time, using O(n) processors. Thus, the time complexity of
For all A = (a,,, . . . , a,-,), let Z(A) = (Z(ao), . . . ,
the above code is bounded by 0( log n) . Let us now calcuZ(a,-,)).Letbin[i:j] =(bin[i:j; l],..., bin[i:j;m]). late the processor count. Let P(n) be the number of procesIt follows from the combination lemma that for all i, 1 < i sors used for a DFI of size n. Since step (2) uses O(n) pro-
<n, cessors, step ( 1) uses 2 P( n/2) processors. Moreover, steps
( 1) and (2) are executed sequentially. Hence, P(n)
bin[ 1 : i] = Z(bin[l : I] 0 . . . 0 bin[n: n]). = max(2P(n/2), O(n)), whichimplies P(n) = O(n). n
Hence, the integral exactly-packing problem is reduced to
the prefix integral convolution problem.
DEFINITION 5.1. An element w in a commutative ring
is called a primitive iVth root of unity if(i) w Z 1; (ii) wN
= 1; (iii) CoGfiN-, wip=O, 1 =Zj<N-- 1.
Without loss of generality, it is assumed that m is a power
of two. Let F,,,( ) be a discrete Fourier transformation
(DFT) over a vector of m elements; i.e., IV, is an m by m
matrix such that Wi,j = wij, and for all x E N”, F,(x)
= Wx. Let F;’ ( ) be the inverse of F,( ). Clearly,
F;‘( ) itself is a discrete Fourier transformation. Let A
= (4, . . . , a,, 0, . . . , 0), B = (b,, . . . , b,, 0, . . . , 0). It
follows from the Convolution Theorem [ 21 that
Therefore,
LEMMA 5.3. {bin[l : i; k] ( 1 < i < n, 2 < k < m}
can be computed in O(log m log n) time, using mnflog m
processors on an ERE W PRAM.
If there exists a solution to an integral exactly-packing
problem, then a solution can be found in 0( log mn) time,
using an mn / log mn processor via the same algorithm given
in Section 3.2. Hence,
THEOREM 5.1. The integral exactly-packing problem
can be solved in O(log n log m) time, using mnflog m processors.
5.1. A Reduction to the Prefix Convolution Problem
AO B = F&F,,(B). F2,(B)).
In this section, a simple, yet more processor-efficient parallel integral knapsack algorithm is shown for the case in
Forallx= (x0,. . . ,x,)T,letx,,,, = (x0,x2,. . . , x,-~)~,
which n < min(m, s).
&Id = (xl, x3, * . . , &-I jT* According to the properties Of
For all i, j, k, 1 < i <j G n, 1 < k < m, let value[ i : j; k]
the discrete Fourier transformation, for all n = 2 k for some
denote the maximum value of SXp( Ji, . . . , Jj, k).
k,forallx= (x0,. . . ,x,JT, Clearly,foralli,k, 1 <i<n, 1 <k<m,
F,(x) = II Fn,2(xeven) + Fn,z(Xodd)
Dn,2Fn,2(xe,d - Dd’n,dXodd)
>
Vi.
where II( ) is a 2mpermutation [ 21, and
Note also that the total value for an optimal combination
for JXP(J1,. . . , J,, m) is equal to value[l : n; m]. The
Dni2=[% “I ::: wu;) *
following lemma can be proven easily via induction.
LEMMA 5.4 (Combination Lemma). For all i, , i2, i3 ( 1
~(k:*~~~:‘;~lue~i,~i2;ll,~~~,value[il:o;ml)
The above relation suggests the following parallel code
for the discrete Fourier transformation.
(b I,..., hn)
= (value[i2 + 1 : i,; 11,. . . , value[i2 + 1 : i3; ml).
Procedure F,(x)
( 1) In p=alleld Fn,2 (x,,,, 1, call Fn12 (%dd ) Then for all k ( 1 < k G m), value[ il : i,; k] = mar+I=k( a,
(2) output + bt).
Fn,2(~even) + Fn/z(-&id)
Let 0, the convolution operator, be a binary operator
F,(x) = II suchthatforallA=(a, ,..., a,),B=(b, ,..., b,)EZm,
Dn,2Fn,2(xeven) - Dn,2Fn,kCxid) ABB=(c,,..., c,) is defined as: for all i, 1 G i < m , C= maxl+,,i(al + b,). Clearly, 0 is associative. Let u[ i : j]
= (vulue[i:j; 11,. . . , value[ i : j; ml). It follows from the
definition of 0 and the Combination Lemma that for all i,
1 <i<?Z,
u[l : i] = v[l : i- l] 0 v[i: i]
= V[l : l] 0 u[2 : 210 * * * 0 v[n: n].
Since, for all i ( 1 <i<n),v[i;i]canbeefficientlycomputed, the integral knapsack problem is reduced to the prefix convolution problem over n vectors of size m . Moreover,
itiseasytoverifythatforallA=(a,,...,u,),B=(b,,
. . . ) b,)EZm,C=(cl,.. .,c,)=A0Bcanbecomputed
in O(log m) time, using O(m2/log m) processors. Using
the standard parallel algorithm for the prefix sum [ 19, 1 I],
{ vulue[l : i; k] 1 1 < i G n, 1 < k < m } can be computed
in O(log m log n) time, using m2n/log n log m processors
on an EREW PRAM. Hence,
THEOREM 5.2. An optimal combination of SXP( J, ,
. , 9,, m) can be computed in 0( log m log n) time, using
m 2n / log m log n processors.
6. FINAL REMARKS
It follows from a recent result5 obtained jointly by the
author and M. Atallah, R. Kasoraju, L. Larmore, and G.
Miller [ 31 that the integral knapsack problem can be solved
in O(log m log n) time, using mn/log n log m processors on
a CREW machine. This is due to the fact that the matrix
used in the algorithm presented in Section 3 of the present
paper is concave [ 31
THEOREM 6.1. An optimal combination of SYfP(S, ,
. . . ) 9,, m) can be computed in O(log n log m) time, using
mm/log m log n processors.
7. OPEN PROBLEM
Can the convolution problem of an m-vector over any
commutative semiring, or over semiring { N, max, + } ), be
solved in 0( m log m) sequential time, or 0( log m) parallel
time, using O(n) processors? 
