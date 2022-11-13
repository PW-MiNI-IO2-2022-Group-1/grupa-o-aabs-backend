// Copyright Epic Games, Inc. All Rights Reserved.
/*===========================================================================
	Generated code exported from UnrealHeaderTool.
	DO NOT modify this manually! Edit the corresponding .h files instead!
===========================================================================*/

#include "UObject/GeneratedCppIncludes.h"
PRAGMA_DISABLE_DEPRECATION_WARNINGS
void EmptyLinkFunctionForGeneratedCodeFPSTest_init() {}
	FPSTEST_API UFunction* Z_Construct_UDelegateFunction_FPSTest_OnPickUp__DelegateSignature();
	FPSTEST_API UFunction* Z_Construct_UDelegateFunction_FPSTest_OnUseItem__DelegateSignature();
	static FPackageRegistrationInfo Z_Registration_Info_UPackage__Script_FPSTest;
	FORCENOINLINE UPackage* Z_Construct_UPackage__Script_FPSTest()
	{
		if (!Z_Registration_Info_UPackage__Script_FPSTest.OuterSingleton)
		{
			static UObject* (*const SingletonFuncArray[])() = {
				(UObject* (*)())Z_Construct_UDelegateFunction_FPSTest_OnPickUp__DelegateSignature,
				(UObject* (*)())Z_Construct_UDelegateFunction_FPSTest_OnUseItem__DelegateSignature,
			};
			static const UECodeGen_Private::FPackageParams PackageParams = {
				"/Script/FPSTest",
				SingletonFuncArray,
				UE_ARRAY_COUNT(SingletonFuncArray),
				PKG_CompiledIn | 0x00000000,
				0x2E57563D,
				0x43DDEACF,
				METADATA_PARAMS(nullptr, 0)
			};
			UECodeGen_Private::ConstructUPackage(Z_Registration_Info_UPackage__Script_FPSTest.OuterSingleton, PackageParams);
		}
		return Z_Registration_Info_UPackage__Script_FPSTest.OuterSingleton;
	}
	static FRegisterCompiledInInfo Z_CompiledInDeferPackage_UPackage__Script_FPSTest(Z_Construct_UPackage__Script_FPSTest, TEXT("/Script/FPSTest"), Z_Registration_Info_UPackage__Script_FPSTest, CONSTRUCT_RELOAD_VERSION_INFO(FPackageReloadVersionInfo, 0x2E57563D, 0x43DDEACF));
PRAGMA_ENABLE_DEPRECATION_WARNINGS
