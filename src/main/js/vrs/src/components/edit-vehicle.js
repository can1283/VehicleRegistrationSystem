import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate, useParams} from "react-router-dom";
import {Formik, Field, Form, ErrorMessage} from "formik";
import * as Yup from "yup";

const EditVehicle = () => {
    const {vehicleId} = useParams();
    const navigation = useNavigate();

    const validationSchema = Yup.object().shape({
        name: Yup.string()
            .min(2, 'Name size must be between 2 and 30 characters')
            .max(30, 'Name size must be between 2 and 30 characters')
            .required('Name is required'),
        brand: Yup.string()
            .min(1, 'Brand size must be between 1 and 16 characters')
            .max(16, 'Brand size must be between 1 and 16 characters')
            .required('Brand is required'),
        model: Yup.string()
            .min(1, 'Model size must be between 1 and 16 characters')
            .max(16, 'Model size must be between 1 and 16 characters')
            .required('Model is required'),
        modelYear: Yup.number().required('Model year is required'),
        plateCode: Yup.string()
            .matches(
                /^(?=.{1,8}$)([0-9]{2}|[01]{2})([A-Za-z]{2,})([0-9]{2,})$/,
                'Plate code is not valid'
            )
            .min(6, 'Plate code size must be between 6 and 8 characters')
            .max(8, 'Plate code size must be between 6 and 8 characters')
            .required('Plate code is required'),
        active: Yup.boolean().required('Active status is required'),
    });

    const [formData, setFormData] = useState({
        name: "",
        brand: "",
        model: "",
        modelYear: 0,
        plateCode: "",
        active: false,
    });

    useEffect(() => {
        axios
            .get(`http://localhost:8080/api/vehicles/${vehicleId}`)
            .then((response) => {
                setFormData({
                    name: response?.data?.name,
                    brand: response?.data?.brand,
                    model: response?.data?.model,
                    modelYear: response?.data?.modelYear,
                    plateCode: response?.data?.plateCode,
                    active: response?.data?.active,
                });
            })
            .catch((error) => {
                console.error("Error fetching vehicle data:", error);
            });
    }, [vehicleId]);


    const handleChange = (e) => {
        const {name, value, type, checked} = e.target;
        const newValue = type === "checkbox" ? checked : value;
        setFormData({...formData, [name]: newValue});
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios
            .put(`http://localhost:8080/api/vehicles/${vehicleId}`, formData)
            .then((response) => {
                console.log("Vehicle updated successfully");
                navigation("/dashboard");
            })
            .catch((error) => {
                console.error("Error updating vehicle:", error);
            });
    };


    return (
        <>
            <div className="bg-gradient-to-t from-blue-700 to-blue-900 w-full h-full absolute"></div>
            <div className="absolute flex items-center justify-center w-full h-full">
                <div className="flex items-center justify-center bg-white rounded-2xl">
                    <Formik
                        initialValues={{
                            name: formData.name,
                            brand: formData.brand,
                            model: formData.model,
                            modelYear: formData.modelYear,
                            plateCode: formData.plateCode,
                            active: formData.active,
                        }}
                        validationSchema={validationSchema}
                        onSubmit={(values, { setSubmitting, setErrors }) => {
                            const { name, brand, model, modelYear, plateCode, active } = values;
                            const payload = {
                                name,
                                brand,
                                model,
                                modelYear,
                                plateCode,
                                active,
                            };

                            axios
                                .put(`http://localhost:8080/api/vehicles/${vehicleId}`, payload)
                                .then((response) => {
                                    console.log("Vehicle updated successfully");
                                    navigation("/dashboard");
                                })
                                .catch((error) => {
                                    console.error("Error updating vehicle:", error);
                                    setSubmitting(false);
                                    setErrors({ submit: "Error updating vehicle" });
                                });
                        }}
                    >
                        <Form className="px-10 py-8 flex flex-col gap-3 w-[500px] shadow-2xl border-2 rounded-2xl">
                            <h2 className="text-2xl font-bold mb-4">Edit Vehicle</h2>
                            <div className="flex flex-col gap-1 text-m font-medium text-gray-900">
                                <label>Name:</label>
                                <Field
                                    type="text"
                                    name="name"
                                    className="p-2 border border-gray-300 rounded-xl outline-none"
                                />
                                <ErrorMessage name="name" component="div" className="text-red-500" />
                            </div>
                            <div className="flex flex-col gap-1 text-m font-medium text-gray-900">
                                <label>Brand:</label>
                                <Field
                                    type="text"
                                    name="brand"
                                    className="p-2 border border-gray-300 rounded-xl outline-none"
                                />
                                <ErrorMessage name="brand" component="div" className="text-red-500" />
                            </div>
                            <div className="flex flex-col gap-1 text-m font-medium text-gray-900">
                                <label>Model:</label>
                                <Field
                                    type="text"
                                    name="model"
                                    className="p-2 border border-gray-300  rounded-xl outline-none"
                                />
                                <ErrorMessage name="model" component="div" className="text-red-500" />
                            </div>
                            <div className="flex flex-col gap-1 text-m font-medium text-gray-900">
                                <label>Model Year:</label>
                                <Field
                                    type="number"
                                    name="modelYear"
                                    className="p-2 border border-gray-300 rounded-xl outline-none"
                                />
                                <ErrorMessage name="modelYear" component="div" className="text-red-500" />
                            </div>
                            <div className="flex flex-col gap-1 text-m font-medium text-gray-900">
                                <label>Plate Code:</label>
                                <Field
                                    type="text"
                                    name="plateCode"
                                    className="p-2 border border-gray-300 rounded-xl outline-none"
                                />
                                <ErrorMessage name="plateCode" component="div" className="text-red-500" />
                            </div>
                            <div className="text-m font-medium text-gray-900">
                                <label>Active:</label>
                                <Field
                                    type="checkbox"
                                    name="active"
                                    className="p-2 border border-gray-300 rounded-xl outline-none ml-2"
                                />
                            </div>
                            <ErrorMessage name="submit" component="p" className="text-red-500" />
                            <div className="flex justify-between gap-2">
                                <button
                                    type="submit"
                                    className="bg-indigo-700 p-2 rounded-xl text-m w-full text-cyan-50 shadow-sm"
                                >
                                    Update Vehicle
                                </button>
                                <Link to="/dashboard">
                                    <button className="bg-indigo-500 text-white rounded-xl w-32 p-2">Back</button>
                                </Link>
                            </div>
                        </Form>
                    </Formik>
                </div>
            </div>
        </>
    );
};

export default EditVehicle;
