/* Objective function */
max: +3 x1 +3 x2 +2 x3 +3 x4 +3 x5 +3 x6 +3 x7 +3 x8 +2 x9 +3 x10 +3 x11 +3 x12 +2 x13 +3 x14 +2 x15 +3 x16
 +2 x17 +3 x18 +3 x19 +3 x20 +3 x21 +3 x22 +3 x23 +3 x24 +3 x25 +2 x26 +3 x27 +3 x28 +2 x29 +3 x30 +3 x31
 +3 x32 +2 x33 +3 x34 +3 x35 +3 x36 +3 x37 +3 x38 +2 x39 +3 x40 +3 x41 +3 x42 +2 x43 +3 x44 +3 x45 +3 x46
 +3 x47 +2 x48 +3 x49 +2 x50 +3 x51 +3 x52 +2 x53 +3 x54 +3 x55 +2 x56 +3 x57 +2 x58 +3 x59 +3 x60 +3 x61
 +3 x62 +3 x63 +3 x64 +3 x65 +2 x66 +3 x67 +3 x68 +2 x69 +3 x70 +3 x71 +3 x72 +3 x73 +2 x74 +3 x75 +3 x76
 +3 x77 +3 x78 +3 x79 +3 x80 +3 x81 +3 x82 +2 x83 +3 x84 +3 x85 +3 x86 +2 x87 +3 x88 +3 x89 +3 x90 +3 x91
 +2 x92 +3 x93 +3 x94 +2 x95 +3 x96 +3 x97 +2 x98 +3 x99 +3 x100 +2 x101 +3 x102 +3 x103 +3 x104 +3 x105
 +2 x106 +3 x107 +3 x108 +3 x109 +3 x110 +2 x111 +3 x112 +3 x113 +3 x114 +3 x115 +2 x116 +3 x117 +3 x118
 +3 x119 +2 x120 +3 x121 +3 x122 +3 x123 +2 x124 +3 x125 +3 x126 +3 x127 +3 x128 +2 x129 +3 x130 +3 x131
 +3 x132 +3 x133 +2 x134 +3 x135 +3 x136 +3 x137 +2 x138 +3 x139 +3 x140 +3 x141 +3 x142 +3 x143 +3 x144
 +3 x145 +3 x146;

/* Constraints */
+x1 +x2 +x3 +x4 +x5 +x6 +x7 +x8 +x9 +x10 +x11 +x12 +x13 +x14 +x18 +x21 +x45 +x50 +x51 +x52 +x59 +x62
 +x70 +x75 +x84 +x87 +x88 +x89 +x90 +x91 +x107 +x112 +x121 +x124 +x125 +x126 +x127 +x128 +x139 <= 1;
+x1 +x2 +x15 +x16 +x17 +x18 +x19 +x20 +x21 +x22 +x23 +x24 +x31 +x32 +x66 +x67 +x68 +x76 +x88 +x92 +x93
 +x94 +x102 +x117 +x125 +x130 +x135 +x140 <= 1;
+x25 +x26 +x27 +x77 +x95 +x96 +x97 +x141 <= 1;
+x28 +x29 +x30 +x78 +x98 +x99 +x100 +x142 <= 1;
+x19 +x22 +x31 +x32 +x33 +x34 +x35 +x36 +x37 +x38 +x39 +x40 +x41 +x42 +x43 +x44 +x46 +x53 +x54 +x55 +x60
 +x63 +x71 +x79 +x85 +x101 +x102 +x103 +x104 +x105 +x108 +x113 +x122 +x129 +x130 +x131 +x132 +x133 +x143 <= 1;
+x10 +x40 +x45 +x46 +x47 +x48 +x49 +x80 +x106 +x107 +x108 +x109 +x110 +x144 <= 1;
+x3 +x4 +x5 +x6 +x11 +x33 +x34 +x35 +x36 +x41 +x50 +x51 +x52 +x53 +x54 +x55 +x56 +x57 +x58 +x59 +x60
 +x61 +x62 +x63 +x64 +x65 +x69 +x70 +x71 +x72 +x73 +x81 +x89 +x103 +x111 +x112 +x113 +x114 +x115 +x118
 +x126 +x131 +x136 +x145 <= 1;
+x6 +x7 +x8 +x15 +x16 +x20 +x23 +x25 +x28 +x36 +x37 +x38 +x47 +x51 +x54 +x56 +x57 +x61 +x64 +x66 +x67
 +x68 +x69 +x70 +x71 +x72 +x73 +x74 +x75 +x76 +x77 +x78 +x79 +x80 +x81 +x82 +x83 +x84 +x85 +x86 +x90
 +x93 +x96 +x99 +x104 +x109 +x114 +x116 +x117 +x118 +x119 +x123 +x127 +x132 +x134 +x135 +x136 +x137 +x146
 +x147 <= 1;
-x6 -x7 -x8 -x15 -x16 -x20 -x23 -x25 -x28 -x36 -x37 -x38 -x47 -x51 -x54 -x56 -x57 -x61 -x64 -x66 -x67
 -x68 -x69 -x70 -x71 -x72 -x73 -x74 -x75 -x76 -x77 -x78 -x79 -x80 -x81 -x82 -x83 -x84 -x85 -x86 -x90
 -x93 -x96 -x99 -x104 -x109 -x114 -x116 -x117 -x118 -x119 -x123 -x127 -x132 -x134 -x135 -x136 -x137 -x146
 -x147 <= -1;
+x1 +x4 +x7 +x9 +x10 +x11 +x12 +x14 +x16 +x17 +x18 +x19 +x20 +x24 +x25 +x26 +x27 +x28 +x29 +x30 +x31
 +x34 +x37 +x39 +x40 +x41 +x42 +x44 +x45 +x46 +x47 +x48 +x49 +x52 +x55 +x57 +x58 +x59 +x60 +x61 +x65
 +x67 +x72 +x74 +x75 +x76 +x77 +x78 +x79 +x80 +x81 +x82 +x86 +x87 +x88 +x89 +x90 +x91 +x92 +x93 +x94
 +x95 +x96 +x97 +x98 +x99 +x100 +x101 +x102 +x103 +x104 +x105 +x106 +x107 +x108 +x109 +x110 +x111 +x112
 +x113 +x114 +x115 +x116 +x117 +x118 +x119 +x120 +x121 +x122 +x123 +x128 +x133 +x137 +x138 +x139 +x140
 +x141 +x142 +x143 +x144 +x145 +x146 +x148 <= 1;
-x1 -x4 -x7 -x9 -x10 -x11 -x12 -x14 -x16 -x17 -x18 -x19 -x20 -x24 -x25 -x26 -x27 -x28 -x29 -x30 -x31
 -x34 -x37 -x39 -x40 -x41 -x42 -x44 -x45 -x46 -x47 -x48 -x49 -x52 -x55 -x57 -x58 -x59 -x60 -x61 -x65
 -x67 -x72 -x74 -x75 -x76 -x77 -x78 -x79 -x80 -x81 -x82 -x86 -x87 -x88 -x89 -x90 -x91 -x92 -x93 -x94
 -x95 -x96 -x97 -x98 -x99 -x100 -x101 -x102 -x103 -x104 -x105 -x106 -x107 -x108 -x109 -x110 -x111 -x112
 -x113 -x114 -x115 -x116 -x117 -x118 -x119 -x120 -x121 -x122 -x123 -x128 -x133 -x137 -x138 -x139 -x140
 -x141 -x142 -x143 -x144 -x145 -x146 -x148 <= -1;
+x2 +x5 +x8 +x12 +x13 +x14 +x21 +x22 +x23 +x24 +x27 +x30 +x32 +x35 +x38 +x42 +x43 +x44 +x49 +x62 +x63
 +x64 +x65 +x68 +x73 +x82 +x83 +x84 +x85 +x86 +x91 +x94 +x97 +x100 +x105 +x110 +x115 +x119 +x120 +x121
 +x122 +x123 +x124 +x125 +x126 +x127 +x128 +x129 +x130 +x131 +x132 +x133 +x134 +x135 +x136 +x137 +x138
 +x139 +x140 +x141 +x142 +x143 +x144 +x145 +x146 +x149 <= 1;
-x2 -x5 -x8 -x12 -x13 -x14 -x21 -x22 -x23 -x24 -x27 -x30 -x32 -x35 -x38 -x42 -x43 -x44 -x49 -x62 -x63
 -x64 -x65 -x68 -x73 -x82 -x83 -x84 -x85 -x86 -x91 -x94 -x97 -x100 -x105 -x110 -x115 -x119 -x120 -x121
 -x122 -x123 -x124 -x125 -x126 -x127 -x128 -x129 -x130 -x131 -x132 -x133 -x134 -x135 -x136 -x137 -x138
 -x139 -x140 -x141 -x142 -x143 -x144 -x145 -x146 -x149 <= -1;

/* Variable bounds */
x1 <= 1;
x2 <= 1;
x3 <= 1;
x4 <= 1;
x5 <= 1;
x6 <= 1;
x7 <= 1;
x8 <= 1;
x9 <= 1;
x10 <= 1;
x11 <= 1;
x12 <= 1;
x13 <= 1;
x14 <= 1;
x15 <= 1;
x16 <= 1;
x17 <= 1;
x18 <= 1;
x19 <= 1;
x20 <= 1;
x21 <= 1;
x22 <= 1;
x23 <= 1;
x24 <= 1;
x25 <= 1;
x26 <= 1;
x27 <= 1;
x28 <= 1;
x29 <= 1;
x30 <= 1;
x31 <= 1;
x32 <= 1;
x33 <= 1;
x34 <= 1;
x35 <= 1;
x36 <= 1;
x37 <= 1;
x38 <= 1;
x39 <= 1;
x40 <= 1;
x41 <= 1;
x42 <= 1;
x43 <= 1;
x44 <= 1;
x45 <= 1;
x46 <= 1;
x47 <= 1;
x48 <= 1;
x49 <= 1;
x50 <= 1;
x51 <= 1;
x52 <= 1;
x53 <= 1;
x54 <= 1;
x55 <= 1;
x56 <= 1;
x57 <= 1;
x58 <= 1;
x59 <= 1;
x60 <= 1;
x61 <= 1;
x62 <= 1;
x63 <= 1;
x64 <= 1;
x65 <= 1;
x66 <= 1;
x67 <= 1;
x68 <= 1;
x69 <= 1;
x70 <= 1;
x71 <= 1;
x72 <= 1;
x73 <= 1;
x74 <= 1;
x75 <= 1;
x76 <= 1;
x77 <= 1;
x78 <= 1;
x79 <= 1;
x80 <= 1;
x81 <= 1;
x82 <= 1;
x83 <= 1;
x84 <= 1;
x85 <= 1;
x86 <= 1;
x87 <= 1;
x88 <= 1;
x89 <= 1;
x90 <= 1;
x91 <= 1;
x92 <= 1;
x93 <= 1;
x94 <= 1;
x95 <= 1;
x96 <= 1;
x97 <= 1;
x98 <= 1;
x99 <= 1;
x100 <= 1;
x101 <= 1;
x102 <= 1;
x103 <= 1;
x104 <= 1;
x105 <= 1;
x106 <= 1;
x107 <= 1;
x108 <= 1;
x109 <= 1;
x110 <= 1;
x111 <= 1;
x112 <= 1;
x113 <= 1;
x114 <= 1;
x115 <= 1;
x116 <= 1;
x117 <= 1;
x118 <= 1;
x119 <= 1;
x120 <= 1;
x121 <= 1;
x122 <= 1;
x123 <= 1;
x124 <= 1;
x125 <= 1;
x126 <= 1;
x127 <= 1;
x128 <= 1;
x129 <= 1;
x130 <= 1;
x131 <= 1;
x132 <= 1;
x133 <= 1;
x134 <= 1;
x135 <= 1;
x136 <= 1;
x137 <= 1;
x138 <= 1;
x139 <= 1;
x140 <= 1;
x141 <= 1;
x142 <= 1;
x143 <= 1;
x144 <= 1;
x145 <= 1;
x146 <= 1;
x147 <= 1;
x148 <= 1;
x149 <= 1;

/* Integer definitions */
int x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12,x13,x14,x15,x16,x17,x18,x19,x20,x21,x22,x23,x24,x25,x26,x27,x28,x29,x30,x31,x32,x33,x34,x35,x36,x37,x38,x39,x40,x41,x42,x43,x44,x45,x46,x47,x48,x49,x50,x51,x52,x53,x54,x55,x56,x57,x58,x59,x60,x61,x62,x63,x64,x65,x66,x67,x68,x69,x70,x71,x72,x73,x74,x75,x76,x77,x78,x79,x80,x81,x82,x83,x84,x85,x86,x87,x88,x89,x90,x91,x92,x93,x94,x95,x96,x97,x98,x99,x100,x101,x102,x103,x104,x105,x106,x107,x108,x109,x110,x111,x112,x113,x114,x115,x116,x117,x118,x119,x120,x121,x122,x123,x124,x125,x126,x127,x128,x129,x130,x131,x132,x133,x134,x135,x136,x137,x138,x139,x140,x141,x142,x143,x144,x145,x146,x147,x148,x149;
